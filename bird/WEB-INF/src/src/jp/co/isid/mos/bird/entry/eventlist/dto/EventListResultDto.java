package jp.co.isid.mos.bird.entry.eventlist.dto;

import java.util.List;

/**
 * @author xyuchida
 *
 */
public class EventListResultDto {

    /**
     * イベントリスト
     */
    private List eventList;

    public List getEventList() {
        return eventList;
    }

    public void setEventList(List eventList) {
        this.eventList = eventList;
    }

    public boolean isEmptyEventList() {
        return getEventList() == null || getEventList().isEmpty();
    }
}
