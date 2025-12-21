package jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.dto;

import java.util.List;

/**
 * ŒŸõŒ‹‰ÊDTO
 * @author xnkusama
 */
public class EntryStaffSearchResultDto {

    private List listStaff;

    public List getListStaff() {
        return listStaff;
    }

    public void setListStaff(List listStaff) {
        this.listStaff = listStaff;
    }

    public int getStaffSearchListSize() {
        return (getListStaff() == null) ? 0 : getListStaff().size();
    }
}
