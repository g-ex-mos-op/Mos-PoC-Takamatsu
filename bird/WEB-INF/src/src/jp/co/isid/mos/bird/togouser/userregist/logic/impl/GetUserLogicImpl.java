/*
 * 作成日: 2008/11/07
 *
 */
package jp.co.isid.mos.bird.togouser.userregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.togouser.userregist.dao.CodCompanyDao;
import jp.co.isid.mos.bird.togouser.userregist.dao.UIBirdUserDao;
import jp.co.isid.mos.bird.togouser.userregist.dao.UITogoUserRirekiDao;
import jp.co.isid.mos.bird.togouser.userregist.dao.UIUserCompanyDao;
import jp.co.isid.mos.bird.togouser.userregist.dto.UITogoUserDto;
import jp.co.isid.mos.bird.togouser.userregist.entity.UIBirdUser;
import jp.co.isid.mos.bird.togouser.userregist.entity.UITogoUserRireki;
import jp.co.isid.mos.bird.togouser.userregist.entity.UIUserCompany;
import jp.co.isid.mos.bird.togouser.userregist.logic.GetUserLogic;
import jp.co.isid.mos.bird.bizadmin.accountedit.entity.CodCompany;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;


/**
 * @author K.Nihonyanagi
 *
 */
public class GetUserLogicImpl implements GetUserLogic{

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BUR001L01";
    
	private UITogoUserRirekiDao tUserDao;
	private UIBirdUserDao bUserDao;
    private UIUserCompanyDao uiUserCompanyDao;
    private CodCompanyDao codCompanyDao;

	
	public UITogoUserDto execute(String userid, UITogoUserDto uiTogoUserDto){

        //１、Dao【統合ユーザー履歴情報．ユーザー情報の取得】を実行する。                       
        //    パラメータ：   パラメータ．ユーザーID                    
        UITogoUserRireki uiTogoUser;
		uiTogoUser = getUITogoUserDao().getUITogoUserRireki(userid);
        

        //２、Dao【BIRDユーザー情報．BIRDユーザー情報の取得】を実行する。                       
        //　　パラメータ：   パラメータ．ユーザーID                    
        UIBirdUser uiBirdUser;
        uiBirdUser = getBirdUserDao().getBirdUser(userid);

    	List listUIUserCompany = getUiUserCompanyDao().getCltUserCompany(userid);
    	UIUserCompany defaultUserCompany = null;
    	if(listUIUserCompany.size()>0) {
    		defaultUserCompany = (UIUserCompany)listUIUserCompany.get(0);
    	}
        //３、１の[統合ユーザー履歴情報]の取得件数＝0　且つ２の[BIRDユーザー情報]の取得件数＝0の場合                      
        //　３-a、 下記をDTOにセットし、リターンする。                     
        //　　　DTO ←　新規[[BIRDユーザ情報]]                        
        //　　　DTO ←　新規作成した[[ユーザー所属会社]]                     
        //　　　DTO ←　変更区分:0(新規)                     
        if (uiTogoUser == null && uiBirdUser == null) {
            uiTogoUserDto.setUiBirdUser(uiBirdUser);
            uiTogoUserDto.setUiUserCompany(defaultUserCompany);
            uiTogoUserDto.setHenkoKbn("0");
            return uiTogoUserDto;
        }
                
        
        //４、１の[統合ユーザー履歴情報]の取得件数＝0 且つ２の[BIRDユーザー情報]の取得件数≠0の場合                      
        //　エラー：E0404 本部ユーザー以外のユーザーで登録されている                  
        //         登録　                 
        if (uiTogoUser == null && uiBirdUser != null){
            throw new CannotExecuteWithReasonException("本部ユーザー以外のユーザーで登録されている", "登録");
        }
        
        
        //５、１の[統合ユーザー履歴情報]の取得件数≠0　且つ２の[BIRDユーザー情報]の取得件数＝0の場合
        if (uiTogoUser != null && uiBirdUser == null) {

            //　５-a、[BIRDユーザー情報]を作成する
            //  　　  ５-a-1、[BIRDユーザー情報]を新規作成し、以下の項目をセットする。
            //　　　　　　[BIRDユーザー情報]．ユーザID          ←　１で取得した[統合ユーザ履歴情報]．社員番号
            //　　　　　　[BIRDユーザー情報]．ユーザ名称        ←　１で取得した[統合ユーザ履歴情報]．氏名
            //　　　　　　[BIRDユーザー情報]．ユーザ名称(カナ)  ←　１で取得した[統合ユーザ履歴情報]．カナ氏名
            //　　　　　　[BIRDユーザー情報]．退職日            ←　１で取得した[統合ユーザ履歴情報]．退職日
            //　　　　　　[BIRDユーザー情報]．パスワード        ←　１で取得した[統合ユーザ履歴情報]．パスワード
            //　　　　　　[BIRDユーザー情報]．部門コード        ←　１で取得した[統合ユーザ履歴情報]．給与所属部門コード ※前0付加
            UIBirdUser uibirdUserNew = new UIBirdUser();
            uibirdUserNew.setUserId(uiTogoUser.getUserId());
            uibirdUserNew.setUserNameKj(uiTogoUser.getUserNameKj());
            uibirdUserNew.setUserNameKana(uiTogoUser.getUserNameKna());
            uibirdUserNew.setTaishokuDt(uiTogoUser.getTaishokuDt());
            uibirdUserNew.setUserPswd(uiTogoUser.getUserPswd().getBytes());
            CodeFormatter formatter = new CodeFormatter(8);     formatter.setFormatPattern("00000000");
            uibirdUserNew.setBumonCd(formatter.format(uiTogoUser.getSalaryCd(), true));
            
            //  ５-a-2、Dao【会社マスタ．会社マスタの検索】を実行する。
            //          パラメータ：１で取得した[統合ユーザ履歴情報]．給与事業所コードの下2ケタ
            List compList = null;
            if (uiTogoUser.getSalaryOfficeCd() != null){
                int strLen = uiTogoUser.getSalaryOfficeCd().length();
                compList = codCompanyDao.getCompany(uiTogoUser.getSalaryOfficeCd().substring(strLen-2,2));
            }
            else{
                compList = codCompanyDao.getCompany("00"); //給与事業所が空の場合自動的にモスを割り当て   
            }
            
            //　５-a-3、[ユーザー所属会社]を新規作成し、以下の項目をセットする
            //　  　[ユーザー所属会社]．ユーザーID  ←　１で取得した[統合ユーザ履歴情報]．社員番号
            //　　　[ユーザー所属会社]．会社コード  ←　５-a-2で取得した[会社マスタ]．会社コード
            //　　　[ユーザー所属会社]．会社名      ←　５-a-2で取得した[会社マスタ]．会社名
            UIUserCompany uiUserCompanyNew = new UIUserCompany();
            uiUserCompanyNew.setUserId(uiTogoUser.getUserId());
            uiUserCompanyNew.setRCompanyCd(((CodCompany)compList.get(0)).getCompanyCd());
            uiUserCompanyNew.setRCompanyName(((CodCompany)compList.get(0)).getCompanyName());
            
            //　　５-b、[統合ユーザー履歴情報]．画面登録フラグ＝1の場合
            //　　　　　５-b-1、、 下記をDTOにセットする。
            //　　　　　　DTO ←　５-a-1で作成した[[BIRDユーザ情報]]
            //　　　　　　DTO ←　５-a-3で作成した[[ユーザー所属会社]]
            //　　　　　　DTO ←　変更区分:1(参照(パスワードリセットのみ可能))
            if ("1".equals(uiTogoUser.getKbnSpare1())){
                uiTogoUserDto.setUiBirdUser(uibirdUserNew);
                uiTogoUserDto.setUiUserCompany(uiUserCompanyNew);
                uiTogoUserDto.setHenkoKbn("1");
            }
            
            //
            //　　５-c、[統合ユーザー履歴情報]．画面登録フラグ≠1の場合
            //　　　　　５-c-1、 下記をDTOにセットする。
            //　　　　　　DTO ←　５-a-1で作成した[[BIRDユーザ情報]]
            //　　　　　　DTO ←　５-a-3で作成した[[ユーザー所属会社]]
            //　　　　　　DTO ←　変更区分:2(参照(パスワードリセットのみ可能))
            else {
                uiTogoUserDto.setUiBirdUser(uibirdUserNew);
                uiTogoUserDto.setUiUserCompany(uiUserCompanyNew);
                uiTogoUserDto.setHenkoKbn("2");
            }
            //add 2020/09/16 xou.zoubun メールアドレスの追加
            uiTogoUserDto.setInputMailAdd1(uiTogoUser.getMailAdd1());
            
        //６、１の[統合ユーザー履歴情報]の取得件数≠0 且つ２の[BIRDユーザー情報]の取得件数≠0の場合
        } else if (uiTogoUser != null && uiBirdUser != null) {          
            //６-b、[統合ユーザー履歴情報]．画面登録フラグ＝1の場合
            if ("1".equals(uiTogoUser.getKbnSpare1())){
                //　　６-b-1、、 下記をDTOにセットする。
                //　　　　DTO ←　２で取得した[[BIRDユーザ情報]]
                //  　　　DTO ←　６-aで取得した[[ユーザー所属会社]]
                //    　　DTO ←　変更区分:1(変更)
                uiTogoUserDto.setUiBirdUser(uiBirdUser);
                uiTogoUserDto.setUiUserCompany(defaultUserCompany);
                uiTogoUserDto.setHenkoKbn("1");
            }
            else {
                //６-c、[統合ユーザー履歴情報]．画面登録フラグ≠1の場合
                //　　６-c-1、 下記をDTOにセットする。
                //  　　　DTO ←　２で取得した[[BIRDユーザ情報]]
                //  　　　DTO ←　6-aで取得した[[ユーザー所属会社]]
                //  　　　DTO ←　変更区分:2(参照(パスワードリセットのみ可能))
                uiTogoUserDto.setUiBirdUser(uiBirdUser);
                uiTogoUserDto.setUiUserCompany(defaultUserCompany);
                uiTogoUserDto.setHenkoKbn("2");
            }
            uiTogoUserDto.getUiBirdUser().setTaishokuDt(
                    uiTogoUser.getTaishokuDt());
            //add 2020/09/16 xou.zoubun メールアドレスの追加
            uiTogoUserDto.setInputMailAdd1(uiTogoUser.getMailAdd1());
        }
            
            
        //    ７、上記で作成したDTOをリターンする。
		return uiTogoUserDto;
	}
	
	public UITogoUserRirekiDao getUITogoUserDao(){
		return tUserDao;
	}
	
	public void setUITogoUserDao(UITogoUserRirekiDao dao){
		this.tUserDao=dao;
	}

    public UIBirdUserDao getBirdUserDao() {
        return bUserDao;
    }

    public void setBirdUserDao(UIBirdUserDao userDao) {
        bUserDao = userDao;
    }

    public UIUserCompanyDao getUiUserCompanyDao() {
        return uiUserCompanyDao;
    }

    public void setUiUserCompanyDao(UIUserCompanyDao uiUserCompanyDao) {
        this.uiUserCompanyDao = uiUserCompanyDao;
    }

    public CodCompanyDao getCodCompanyDao() {
        return codCompanyDao;
    }

    public void setCodCompanyDao(CodCompanyDao codCompanyDao) {
        this.codCompanyDao = codCompanyDao;
    }

}
