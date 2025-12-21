/**
 * 
 */
package jp.co.isid.mos.bird.commonform.rolesearch.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.commonform.rolesearch.dao.UIUserRoleDao;
import jp.co.isid.mos.bird.commonform.rolesearch.dto.RoleSearchUserListDto;
import jp.co.isid.mos.bird.commonform.rolesearch.entity.UIUserRole;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;

/**
 * ユーザー一覧CSVダウンロードロジック
 * 
 * 作成日:2009/04/23
 * @author xkinu
 *
 */
public class CsvUserListLogicImpl implements CsvOutputLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BCO004L03";
    /**
	 * ユーザロール情報DAO
	 */
	private UIUserRoleDao uIUserRoleDao;
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getFileName(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public String getFileName(CsvOutputDto csvOutputDto) {
		// TODO 自動生成されたメソッド・スタブ
		return "USERLIST.csv";
	}

	/**
	 *  CSV出力データ検索とデータの組込処理
	 * 
	 *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto) {
		RoleSearchUserListDto roleSearchUserListDto = (RoleSearchUserListDto)csvOutputDto;
		// ユーザロールリスト取得
		List listSearchData = getUIUserRoleDao().select(roleSearchUserListDto.getSelectedRoleCd());

		if (listSearchData == null || listSearchData.size() <= 0) {
			throw new NotExistException("対象ユーザー");
		}
		//２．List[[CSV出力用リスト]]を生成します。
        List listCSV = new ArrayList();
        /** 1行目作成 */
        List listHeader1Row = new ArrayList();
        //キャンペーン情報
        listHeader1Row.add("分類：");
        listHeader1Row.add(roleSearchUserListDto.getBunruiName());
        listCSV.add(listHeader1Row);

        /** 2行目作成 */
        List listHeader2Row = new ArrayList();
        //検索対象キャンペーン期間情報
        listHeader2Row.add("ロール名：");
        listHeader2Row.add(roleSearchUserListDto.getRoleName());
        listCSV.add(listHeader2Row);
        
        listCSV.add(new ArrayList(0));
        
        //検索データ項目行の作成。
        List rowTitle = new ArrayList(0);
        rowTitle.add("ユーザーID");
        rowTitle.add("ユーザー名");
        rowTitle.add("ユーザータイプ");
        rowTitle.add("会社");
        rowTitle.add("部門コード");
        rowTitle.add("部門名称");
        rowTitle.add("オーナーコード");
        rowTitle.add("オーナー名称");
        rowTitle.add("店コード");
        rowTitle.add("店名称");
        listCSV.add(rowTitle);
        
        //検索上をを一行づつ格納します。
		for(int i=0; i<listSearchData.size(); i++) {
			UIUserRole uiUserRole = (UIUserRole)listSearchData.get(i);
			List rowData = new ArrayList(0);
			rowData.add(uiUserRole.getUserId());
			rowData.add(uiUserRole.getUserNameKj());
			rowData.add(uiUserRole.getUsertypeName());//ユーザータイプ名称
			rowData.add(uiUserRole.getCompanyName());//会社名称
			if(UserType.HONBU.equals(uiUserRole.getUsertypeCd())) {
				rowData.add(uiUserRole.getBumonCd());
				rowData.add(uiUserRole.getBumonName());
				rowData.add("");
				rowData.add("");
				rowData.add("");
				rowData.add("");
			}
			else if(UserType.ONER.equals(uiUserRole.getUsertypeCd())) {
				rowData.add("");
				rowData.add("");
				rowData.add(uiUserRole.getOnerCd());
				rowData.add(uiUserRole.getOnerNameKj());
				rowData.add("");
				rowData.add("");
			}
			else if(UserType.TENPO.equals(uiUserRole.getUsertypeCd())) {
				rowData.add("");
				rowData.add("");
				rowData.add("");
				rowData.add("");
				rowData.add(uiUserRole.getMiseCd());
				rowData.add(uiUserRole.getMiseNameKj());
			}
			else {
				rowData.add("");
				rowData.add("");
				rowData.add("");
				rowData.add("");
				rowData.add("");
				rowData.add("");
			}
			listCSV.add(rowData);
		}
		return listCSV;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#validate(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public void validate(CsvOutputDto csvOutputDto) {
        // 画面DTO
        if (csvOutputDto == null) {
            throw new NotNullException("ロール選択：ユーザー一覧DTO");
        }
	}
	/**
	 * @return uIUserRoleDao を戻します。
	 */
	public UIUserRoleDao getUIUserRoleDao() {
		return uIUserRoleDao;
	}

	/**
	 * @param userRoleDao を クラス変数uIUserRoleDaoへ設定します。
	 */
	public void setUIUserRoleDao(UIUserRoleDao userRoleDao) {
		uIUserRoleDao = userRoleDao;
	}

}
