package com.school.haja.service.event;

import com.school.haja.endpoint.event.model.SubscriptionConfirmationRequested;
import com.school.haja.mail.Email;
import com.school.haja.mail.Mailer;
import jakarta.mail.internet.InternetAddress;
import java.io.File;
import java.util.List;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubscriptionConfirmationRequestedService
    implements Consumer<SubscriptionConfirmationRequested> {

  private final Mailer mailer;

  @SneakyThrows
  @Override
  public void accept(SubscriptionConfirmationRequested event) {
    InternetAddress recipient = new InternetAddress(event.getTo());

    File pdfFile = SubscriptionConfirmationPdfGenerator.generate(event);

    String body =
        "Bonjour "
            + event.getUserFirstName()
            + ",\n\nVotre inscription au cours \""
            + event.getCourseTitle()
            + "\" a bien ete enregistree avec succes.\n\n"
            + "Vous trouverez le recapitulatif en pièce jointe.\n\n"
            + "A bientot !";

    try {
      mailer.accept(
          new Email(
              recipient,
              List.of(),
              List.of(),
              "Confirmation d'inscription",
              body,
              List.of(pdfFile)));
    } finally {
      pdfFile.delete();
    }
  }
}
