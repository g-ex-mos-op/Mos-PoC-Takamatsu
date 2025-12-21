/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.staffregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.storemanage.staffregist.dao.MstStaffDao;
import jp.co.isid.mos.bird.storemanage.staffregist.dto.StaffRegistDto;
import jp.co.isid.mos.bird.storemanage.staffregist.entity.MstStaff;
import jp.co.isid.mos.bird.storemanage.staffregist.logic.CheckDuplicateNameLogic;

/**
 * 同姓同名スタッフ存在チェックロジック
 * 
 * 作成日:2009/08/28
 * @author xkinu
 *
 */
public class CheckDuplicateNameLogicImpl implements CheckDuplicateNameLogic {
	
	/* ロジックID */
    public static final String LOGIC_ID = "BSM007L10";
    
	/** DAO【スタッフ情報】*/
	private MstStaffDao mstStaffDao;

    /**
     * 
     * @return true：変更されている　false：変更されていない
     */
	/**
	 * 事前条件処理
	 * 
	 * @param dto
	 */
	private void validate(StaffRegistDto staffRegistDto) {
        MstStaff mstStaff = staffRegistDto.getMstStaff();
		if(mstStaff == null) {
			throw new MissingDataException("チェック対象のスタッフ情報");
		}
    }
	/**
	 * 
	 * 同姓同名スタッフ存在チェック処理実行
	 * 
	 * @param mstStaff
	 * @return boolean true:同姓同名有り false:同姓同名無し
	 */
	public boolean execute(StaffRegistDto staffRegistDto) {
        MstStaff mstStaff = staffRegistDto.getMstStaff();
		//１．事前条件処理
		validate(staffRegistDto);
		//DAO【スタッフ情報】同姓同名スタッフ情報検索を実行します。
		List listDuplicate = getMstStaffDao().selectDuplicateName(mstStaff);
		if(listDuplicate.size() > 0) {
			return true;
		}
		return false;
	}
	/** DAO【スタッフ情報】取得処理 */
	public MstStaffDao getMstStaffDao() {
		return mstStaffDao;
	}
	/** DAO【スタッフ情報】設定処理 */
	public void setMstStaffDao(MstStaffDao mstStaffDao) {
		this.mstStaffDao = mstStaffDao;
	}
}
