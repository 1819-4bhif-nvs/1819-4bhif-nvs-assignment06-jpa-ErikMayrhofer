package at.htl.kursverwaltung.core;

import javax.json.bind.adapter.JsonbAdapter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public abstract class AbstractGenericAdapter<Original, Serialized> extends XmlAdapter<Serialized, Original> implements JsonbAdapter<Original, Serialized> {

    @Override
    public Original unmarshal(Serialized s){
        return fromSerialized(s);
    }

    @Override
    public Serialized marshal(Original localDateTime){
        return toSerialized(localDateTime);
    }


    @Override
    public Serialized adaptToJson(Original localDateTime){
        return toSerialized(localDateTime);
    }

    @Override
    public Original adaptFromJson(Serialized s) {
        return fromSerialized(s);
    }

    protected abstract Serialized toSerialized(Original localDateTime);
    protected abstract Original fromSerialized(Serialized string);
}
