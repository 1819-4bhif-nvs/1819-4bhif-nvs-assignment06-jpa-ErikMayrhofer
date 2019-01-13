package at.htl.kursverwaltung.rest;

import at.htl.kursverwaltung.model.Enrolment;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Stateless
@Path("enrolments")
public class EnrolmentEndpoint extends AbstractEndpoint<Enrolment> {
    public EnrolmentEndpoint() {
        super(Enrolment.class);
    }
}
