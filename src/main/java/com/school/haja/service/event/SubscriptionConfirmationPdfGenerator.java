package com.school.haja.service.event;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.school.haja.endpoint.event.model.SubscriptionConfirmationRequested;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class SubscriptionConfirmationPdfGenerator {

  private static final DateTimeFormatter DATE_FORMAT =
      DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone(ZoneId.of("Indian/Antananarivo"));

  private SubscriptionConfirmationPdfGenerator() {}

  public static File generate(SubscriptionConfirmationRequested event) {
    String html = buildHtml(event);
    File file = newTempFile(event);
    try (FileOutputStream out = new FileOutputStream(file)) {
      PdfRendererBuilder builder = new PdfRendererBuilder();
      builder.useFastMode();
      builder.withHtmlContent(html, null);
      builder.toStream(out);
      builder.run();
      return file;
    } catch (IOException e) {
      throw new IllegalStateException("Impossible de generer le PDF de confirmation", e);
    }
  }

  private static File newTempFile(SubscriptionConfirmationRequested event) {
    String suffix =
        event.getSubscriptionId() != null ? event.getSubscriptionId().toString() : "unknown";
    return new File("/tmp/confirmation-inscription-" + suffix + ".pdf");
  }

  private static String buildHtml(SubscriptionConfirmationRequested event) {
    String courseStart =
        event.getCourseStart() != null ? DATE_FORMAT.format(event.getCourseStart()) : "-";
    String courseEnd =
        event.getCourseEnd() != null ? DATE_FORMAT.format(event.getCourseEnd()) : "-";

    return """
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8"/>
    <style>
      body { font-family: Helvetica, Arial, sans-serif; color: #222; margin: 40px; }
      h1 { font-size: 20px; color: #1a1a1a; margin-bottom: 4px; }
      .status { font-size: 13px; color: #2e7d32; margin-bottom: 24px; }
      h2 { font-size: 15px; margin-bottom: 6px; border-bottom: 1px solid #ddd; padding-bottom: 4px; }
      .section { margin-bottom: 20px; }
      .row { font-size: 12px; margin: 3px 0; }
      .label { font-weight: bold; display: inline-block; width: 140px; }
      .footer { margin-top: 30px; font-size: 12px; color: #444; }
    </style>
  </head>
  <body>
    <h1>Confirmation d'inscription</h1>
    <div class="status">Statut : Inscription reussie</div>

    <div class="section">
      <h2>Informations utilisateur</h2>
      <div class="row"><span class="label">Nom complet :</span> %s %s</div>
      <div class="row"><span class="label">Nom d'utilisateur :</span> %s</div>
      <div class="row"><span class="label">Email :</span> %s</div>
    </div>

    <div class="section">
      <h2>Informations cours</h2>
      <div class="row"><span class="label">Titre :</span> %s</div>
      <div class="row"><span class="label">Debut :</span> %s</div>
      <div class="row"><span class="label">Fin :</span> %s</div>
    </div>

    <div class="footer">
      Votre inscription a ete enregistree avec succes. Bienvenue !
    </div>
  </body>
</html>
"""
        .formatted(
            escape(event.getUserFirstName()),
            escape(event.getUserLastName()),
            escape(event.getUserName()),
            escape(event.getUserEmail()),
            escape(event.getCourseTitle()),
            courseStart,
            courseEnd);
  }

  private static String escape(String value) {
    if (value == null) {
      return "";
    }
    return value
        .replace("&", "&amp;")
        .replace("<", "&lt;")
        .replace(">", "&gt;")
        .replace("\"", "&quot;");
  }
}
