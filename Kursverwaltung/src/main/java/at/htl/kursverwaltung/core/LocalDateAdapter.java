package at.htl.kursverwaltung.core;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;

@Named
public class LocalDateAdapter extends AbstractGenericAdapter<LocalDateTime, String> {


    @Override
    protected String toSerialized(LocalDateTime localDateTime){
        return localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    @Override
    protected LocalDateTime fromSerialized(String string){
        try {
            LocalDateTime date = LocalDateTime.parse(string, DateTimeFormatter.ISO_DATE_TIME);
            return date;
        }catch(DateTimeParseException e){
            Logger.getAnonymousLogger().warning("Could not parse Date: "+e.getMessage());
            throw e;
        }
    }

}
