package pl.nzkml.datasource.model;

import pl.nzkml.datasource.model.categoryListElement.TransportElement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transport {
    String transportID;
    Date date;
    Boolean accepted;
    List<TransportElement> transportElements = new ArrayList<>();

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public String getTransportID() {
        return transportID;
    }

    public void setTransportID(String transportID) {
        this.transportID = transportID;
    }

    public List<TransportElement> getTransportElements() {
        return transportElements;
    }

    public void setTransportElements(List<TransportElement> transportElements) {
        this.transportElements = transportElements;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "transportID='" + transportID + '\'' +
                ", date=" + date +
                ", accepted=" + accepted +
                ", transportElements=" + transportElements +
                '}';
    }
}
