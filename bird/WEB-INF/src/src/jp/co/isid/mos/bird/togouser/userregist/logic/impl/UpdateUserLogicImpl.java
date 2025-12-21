/*
 * 作成日: 2008/11/13
 */
package jp.co.isid.mos.bird.togouser.userregist.logic.impl;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.common.dao.CtlConvShozokuCompDao;
import jp.co.isid.mos.bird.common.entity.CtlConvShozokuComp;
import jp.co.isid.mos.bird.common.logic.UpdatePasswordLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.exception.GenericCommentException;
import jp.co.isid.mos.bird.framework.exception.InvalidDataException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.togouser.userregist.dao.UIBirdUserDao;
import jp.co.isid.mos.bird.togouser.userregist.dao.UITogoUserRirekiDao;
import jp.co.isid.mos.bird.togouser.userregist.dao.UIUserCompanyDao;
import jp.co.isid.mos.bird.togouser.userregist.dao.UIUserShozokuDao;
import jp.co.isid.mos.bird.togouser.userregist.dto.UITogoUserDto;
import jp.co.isid.mos.bird.togouser.userregist.entity.RankCode;
import jp.co.isid.mos.bird.togouser.userregist.entity.UIBirdUser;
import jp.co.isid.mos.bird.togouser.userregist.entity.UITogoUserRireki;
import jp.co.isid.mos.bird.togouser.userregist.entity.UIUserCompany;
import jp.co.isid.mos.bird.togouser.userregist.entity.UIUserShozoku;
import jp.co.isid.mos.bird.togouser.userregist.logic.GetRankCodeLogic;
import jp.co.isid.mos.bird.togouser.userregist.logic.UpdateUserLogic;

/**
 * ユーザ情報の更新処理ロジック
 * @author K.Nihonyanagi
 */
public class UpdateUserLogicImpl implements UpdateUserLogic {
    
    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BUR001L05";
    
    private CtlConvShozokuCompDao ctlConvShozokuCompDao;
    private UITogoUserRirekiDao uiTogoUserRirekiDao;
    private UIBirdUserDao uiBirdUserDao;
    private UIUserCompanyDao uiUserCompanyDao;
    private UIUserShozokuDao uiUserShozokuDao;

    private UpdatePasswordLogic updatePasswordLogic;
    private GetRankCodeLogic getRankCodeLogic;
    
    private String PGM_ID = LOGIC_ID.substring(0, 6);
    
    private String sysDate;
    
    /**
     * 統合ユーザの登録
     * @param userRegistDto
     */
    public void execute(UITogoUserDto uiTogoUserDto) {
        
        //isStopFlgメソッド用に、システム日付を入れておく。
        setSysDate(uiTogoUserDto.getBirdDateInfo().getSysDate());
        
        //Ⅰ、パラメータ．変更区分＝0（新規）の場合
        if ("0".equals(uiTogoUserDto.getHenkoKbn().toString())) {
            registSinkiUser(uiTogoUserDto);
        }
        
        //Ⅱ、パラメータ．変更区分＝1（変更）の場合
        else if ("1".equals(uiTogoUserDto.getHenkoKbn().toString())) {
            registHenkoUser(uiTogoUserDto);
        }
        
        //Ⅲ、パラメータ．変更区分＝2（2：参照(パスワードリセットのみ可能)）の場合
        else if ("2".equals(uiTogoUserDto.getHenkoKbn().toString())) {
            registSanshoUser(uiTogoUserDto);
        }
        
    }

    /**
     * 新規ユーザの登録ロジック
     * @param uiTogoUserDto
     */
    private void registSinkiUser(UITogoUserDto uiTogoUserDto) {
        //使用するクラスのデータをDTOから抽出する
        UIBirdUser uiBirdUser = uiTogoUserDto.getUiBirdUser();
        UIUserCompany uiUserCompany = uiTogoUserDto.getUiUserCompany();
        UIUserShozoku uiUserShozoku = uiTogoUserDto.getUiUserShozoku();
        
        
        //１、Dao【統合ユーザー履歴情報．ユーザー情報の取得】を実行する。 
        //　　　　パラメータ：  パラメータ．[BIRDユーザー情報]．ユーザID
        //２、Dao【BIRDユーザー情報．BIRDユーザー情報の取得】を実行する。 
        //　　　　パラメータ：  パラメータ．[BIRDユーザー情報]．ユーザID
        //３、１の[統合ユーザー履歴情報]の取得件数≠0 または、２の[BIRDユーザー情報]の取得件数≠0の場合  
        //　　　　E0607("そのユーザーは登録済み")  
        if (getUiTogoUserRirekiDao().getUITogoUserRireki(uiBirdUser.getUserId()) != null
             || getUiBirdUserDao().getBirdUser(uiBirdUser.getUserId()) != null) {
            throw new GenericCommentException("そのユーザーは登録済み");
        }
        
        //４、[統合ユーザー履歴情報]を新規登録する。
        UITogoUserRireki uiTuserNew = new UITogoUserRireki();
            //４-a、新規．[統合ユーザー履歴情報]を作成し、以下の項目をセットする。
            uiTuserNew = setUITogoUserRireki(uiTogoUserDto);
            //４-c、Dao【統合ユーザ履歴情報．統合ユーザ履歴情報の新規登録】を実行する。
            //      パラメータ：４-aで作成した新規．[統合ユーザ履歴情報]  
            getUiTogoUserRirekiDao().insertTogoUser(uiTuserNew);
        
        //５、[BIRDユーザ情報]を新規登録する。 
        //　　５-a、パラメータ．[BIRDユーザ情報]を編集する。 
        uiBirdUser = setUIBirdUser(uiTogoUserDto);
            //　　５-b、Dao【BIRDユーザ情報．BIRDユーザ情報の新規登録】を実行する。 
            //            　　　　 パラメータ：パラメータ．[BIRDユーザー情報]  
            getUiBirdUserDao().insertBirdUser(uiBirdUser);

        //６、選択された部門コードから、[ユーザ所属会社]を新規登録する。
        List listShozoku = getCtlConvShozokuCompDao().getUserShozokuComapany(uiTogoUserDto.getUiBirdUser().getBumonCd().substring(3, 8));
        Timestamp tmsp = DateManager.getCurrentTimestamp();
        for (Iterator ite = listShozoku.iterator(); ite.hasNext();) {
            CtlConvShozokuComp entity = (CtlConvShozokuComp) ite.next();
	        uiUserCompany = new UIUserCompany();
	        uiUserCompany.setUserId(uiTogoUserDto.getUiBirdUser().getUserId());
	        uiUserCompany.setRCompanyCd(entity.getRCompanyCd());
	        uiUserCompany.setZokuseiKbn(entity.getZokuseiKbn());
	        uiUserCompany.setFirstUser(uiTogoUserDto.getLoginUserId());
	        uiUserCompany.setFirstPgm(LOGIC_ID.substring(0, 6));
	        uiUserCompany.setFirstTmsp(tmsp);
	        uiUserCompany.setLastUser(uiTogoUserDto.getLoginUserId());
	        uiUserCompany.setLastPgm(LOGIC_ID.substring(0, 6));
	        uiUserCompany.setLastTmsp(tmsp);
            //    ６-b、Dao【ユーザ所属会社．ユーザ所属会社の新規登録】を実行する。
            //         　　　    パラメータ：上記で編集した[[ユーザー所属会社]]
            getUiUserCompanyDao().insertUserCompany(uiUserCompany);
        }// end of for

        //７、[ユーザ所属]を新規登録する。 
        //    ７-a、以下の項目を編集する。
        //          ・パラメータ．[ユーザ所属].ユーザID     ←　パラメータ．[BIRDユーザ情報]．ユーザID
        //          ・ パラメータ．[ユーザ所属社].所属区分    ← ’1’（本部）
        uiUserShozoku = setUiUserShozoku(uiTogoUserDto);
            //７-b、Dao【ユーザ所属．ユーザ所属の新規登録】を実行する。
            //　    パラメータ：上記で編集した[ユーザー所属]  
            getUiUserShozokuDao().insertUserShozoku(uiUserShozoku);
        
     
        //８、パラメータ．[BIRDユーザ情報]．パスワード≠ブランクの場合、
        //　　８-a、共通ロジック【パスワードの更新】を実行する。  
        //　　　　パラメータ：  パラメータ．[BIRDユーザ情報]．ユーザID、
        //     パラメータ．[BIRDユーザ情報]．パスワード、
        //     0（新規）、
        //     ログインユーザのユーザーID、
        //     "BUR001"
        if (!"".equals(new String (uiTogoUserDto.getUiBirdUser().getUserPswd()))){
            getUpdatePasswordLogic().execute(
                    uiTogoUserDto.getUiBirdUser().getUserId(),
                    new String(uiTogoUserDto.getUiBirdUser().getUserPswd()),
                    0,
                    uiTogoUserDto.getLoginUserId(),
                    "BUR001");
        }
        

    }


    /**
     * 変更ユーザの登録ロジック
     * @param uiTogoUserDto
     */
    private void registHenkoUser(UITogoUserDto uiTogoUserDto) {
        //使用するクラスのデータをDTOから抽出する
        UIBirdUser uiBirdUser = uiTogoUserDto.getUiBirdUser();
        UIUserCompany uiUserCompany = uiTogoUserDto.getUiUserCompany();
        
        //１、[統合ユーザ履歴情報]を変更する。                 
        //１-a、Dao【統合ユーザ履歴情報．発令日でのユーザー情報の取得】を実行する。
        //       　　　パラメータ：パラメータ．[BIRDユーザー情報]．ユーザーID      
        UITogoUserRireki uiTogoUser = getUiTogoUserRirekiDao().getUITogoUserRireki(uiBirdUser.getUserId());
        
        //ランクとコードをセットする。
        RankCode rankCode = getGetRankCodeLogic().execute(zeroSupress(uiBirdUser.getBumonCd()));
        uiTogoUser.setSalarySectionRank(rankCode.getSoshikiRank());
        uiTogoUser.setSalarySectionCd(rankCode.getSoshikiCd());
        uiTogoUser.setJinjiSectionRank(rankCode.getSoshikiRank());
        uiTogoUser.setJinjiSectionCd(rankCode.getSoshikiCd());
        
        //LAST系2兄弟のセット(LastTimestampをセットするとバグになるのでセットしない)
        uiTogoUser.setLastUser(uiTogoUserDto.getLoginUserId());
        uiTogoUser.setLastPgm(PGM_ID);
        //１-b、取得した[統合ユーザ履歴情報]を最初のデータ．[[発令日]]＝システム日付の場合
        if (uiTogoUser.getHatsureiDt().equals(uiTogoUserDto.getBirdDateInfo().getSysDate()) ) {
            //１-b-1、[統合ユーザ履歴情報]を最初のデータの以下項目を編集する
            //　[統合ユーザー履歴情報].氏名         ←　パラメータ．[BIRDユーザ情報]．ユーザ名称
            uiTogoUser.setUserNameKj(uiBirdUser.getUserNameKj());
            //　[統合ユーザー履歴情報].カナ氏名           ←　パラメータ．[BIRDユーザ情報]．ユーザ名称(カナ)
            uiTogoUser.setUserNameKna(uiBirdUser.getUserNameKana());
            //　[統合ユーザー履歴情報].メールアドレス         ←　パラメータ．メールアドレス add 2020/09/16 xou.zoubun
            uiTogoUser.setMailAdd1(uiTogoUserDto.getInputMailAdd1());
            //　[統合ユーザー履歴情報].退職日            ←　パラメータ．[BIRDユーザ情報]．退職日
            uiTogoUser.setTaishokuDt(uiBirdUser.getTaishokuDt());
            //　[統合ユーザー履歴情報].パスワード          ←　パラメータ．[BIRDユーザ情報]．パスワード
            if (!"".equals(new String(uiBirdUser.getUserPswd()))){
                uiTogoUser.setUserPswd(new String(uiBirdUser.getUserPswd()));
            }
            //パスワードの入力がない場合、現在のパスワードのまま更新する。
            else{
                UIBirdUser uiBirdU = getUiBirdUserDao().getBirdUser(uiBirdUser.getUserId());
                uiTogoUser.setUserPswd(new String(uiBirdU.getUserPswd()));
            }
            //　[統合ユーザー履歴情報].給与所属部門コード          ←　パラメータ．[BIRDユーザ情報]．部門コード　※前0除去     
            uiTogoUser.setSalaryCd(zeroSupress(uiBirdUser.getBumonCd()));
            //　[統合ユーザー履歴情報].人事所属部門コード          ←　パラメータ．[BIRDユーザ情報]．部門コード　※前0除去
            uiTogoUser.setJinjiCd(zeroSupress(uiBirdUser.getBumonCd()));
            uiTogoUser.setSararyName(uiBirdUser.getBumonName());
            uiTogoUser.setJinjiName(uiBirdUser.getBumonName());
                        
            //  [統合ユーザー履歴情報].給与事業所コード←　パラメータ．[ユーザ所属会社]．会社コード(2桁)を2重で登録 
            uiTogoUser.setSalaryOfficeCd(uiUserCompany.getRCompanyCd() + uiUserCompany.getRCompanyCd());
            //　[統合ユーザー履歴情報].人事事業所コード←　パラメータ．[ユーザ所属会社]．会社コード(2桁)を2重で登録 
            uiTogoUser.setJinjiOfficeCd(uiUserCompany.getRCompanyCd() + uiUserCompany.getRCompanyCd());
            //　[統合ユーザー履歴情報].給与事業所名称 ←　[ユーザ所属会社]．会社名称
            uiTogoUser.setSalaryOfficeName(uiUserCompany.getRCompanyName());
            //　[統合ユーザー履歴情報].人事事業所名称 ←　[ユーザ所属会社]．会社名称
            uiTogoUser.setJinjiOfficeName(uiUserCompany.getRCompanyName());

            //　[統合ユーザー履歴情報].画面登録フラグ            ←　'1'
            uiTogoUser.setKbnSpare1("1");
            
            
            //１-b-2、Dao【統合ユーザ履歴情報．ユーザー情報の更新】を実行する
            //　　　パラメータ：上記で編集した[統合ユーザ履歴情報]
            getUiTogoUserRirekiDao().updateUITogoUserRireki(uiTogoUser);
        }
        //１-ｃ、取得した[統合ユーザ履歴情報]を最初のデータ．[[発令日]]＜システム日付の場合                    
        else if (uiTogoUser.getHatsureiDt().compareTo(uiTogoUserDto.getBirdDateInfo().getSysDate()) < 0) {
            //１-c-1、[統合ユーザ履歴情報]を最初のデータの以下項目を編集する                   
            //　[統合ユーザー履歴情報].氏名         ←　パラメータ．[BIRDユーザ情報]．ユーザ名称       
            uiTogoUser.setUserNameKj(uiBirdUser.getUserNameKj());
            //　[統合ユーザー履歴情報].発令日            ←　システム日付
            uiTogoUser.setHatsureiDt(uiTogoUserDto.getBirdDateInfo().getSysDate());
            //　[統合ユーザー履歴情報].カナ氏名           ←　パラメータ．[BIRDユーザ情報]．ユーザ名称(カナ)
            uiTogoUser.setUserNameKna(uiBirdUser.getUserNameKana());
            //　[統合ユーザー履歴情報].メールアドレス         ←　パラメータ．メールアドレス add 2020/09/16 xou.zoubun
            uiTogoUser.setMailAdd1(uiTogoUserDto.getInputMailAdd1());
            //　[統合ユーザー履歴情報].退職日            ←　パラメータ．[BIRDユーザ情報]．退職日
            uiTogoUser.setTaishokuDt(uiBirdUser.getTaishokuDt());
            
            
            //[統合ユーザー履歴情報].使用停止フラグ ←　ブランク
            if("".equals(uiBirdUser.getTaishokuDt())) {
                uiTogoUser.setStopFlg("");
            }else{
                //[統合ユーザー履歴情報].退職日が入力されている場合で、かつ、
                //退職日がシステム日付より過去の場合、ストップフラグを１にする。
                if ( isStopFlg( uiBirdUser.getTaishokuDt())) {
                    uiTogoUser.setStopFlg("1");
                } else {
                    uiTogoUser.setStopFlg("");
                }
                
            }
            
            //　[統合ユーザー履歴情報].パスワード          ←　パラメータ．[BIRDユーザ情報]．パスワード
            if (!"".equals(new String(uiBirdUser.getUserPswd()))){
                uiTogoUser.setUserPswd(new String(uiBirdUser.getUserPswd()));
            }
            //パスワードの入力がない場合、現在のパスワードのまま更新する。
            else{
                UIBirdUser uiBirdU = getUiBirdUserDao().getBirdUser(uiBirdUser.getUserId());
                uiTogoUser.setUserPswd(new String(uiBirdU.getUserPswd()));
            }
            //　[統合ユーザー履歴情報].給与所属部門コード          ←　パラメータ．[BIRDユーザ情報]．部門コード　※前0除去
            uiTogoUser.setSalaryCd(zeroSupress(uiBirdUser.getBumonCd()));
            //　[統合ユーザー履歴情報].人事所属部門コード          ←　パラメータ．[BIRDユーザ情報]．部門コード　※前0除去
            uiTogoUser.setJinjiCd(zeroSupress(uiBirdUser.getBumonCd()));
            //  [統合ユーザー履歴情報].給与所属部門名称          ←　パラメータ．[BIRDユーザ情報]．部門名称
            uiTogoUser.setSararyName(uiBirdUser.getBumonName());
            //  [統合ユーザー履歴情報].人事所属部門名称          ←　パラメータ．[BIRDユーザ情報]．部門名称
            uiTogoUser.setJinjiName(uiBirdUser.getBumonName());
            
            //  [統合ユーザー履歴情報].給与事業所コード←　パラメータ．[ユーザ所属会社]．会社コード(2桁)を2重で登録 
            uiTogoUser.setSalaryOfficeCd(uiUserCompany.getRCompanyCd() + uiUserCompany.getRCompanyCd());
            //　[統合ユーザー履歴情報].人事事業所コード←　パラメータ．[ユーザ所属会社]．会社コード(2桁)を2重で登録 
            uiTogoUser.setJinjiOfficeCd(uiUserCompany.getRCompanyCd() + uiUserCompany.getRCompanyCd());
            //　[統合ユーザー履歴情報].給与事業所名称 ←　[ユーザ所属会社]．会社名称
            uiTogoUser.setSalaryOfficeName(uiUserCompany.getRCompanyName());
            //　[統合ユーザー履歴情報].人事事業所名称 ←　[ユーザ所属会社]．会社名称
            uiTogoUser.setJinjiOfficeName(uiUserCompany.getRCompanyName());
            
            
            //　[統合ユーザー履歴情報].画面登録フラグ            ←　'1'       
            uiTogoUser.setKbnSpare1("1");
            
            //１-c-2、Dao【統合ユーザ履歴情報．ユーザー情報の新規登録】を実行する                    
            //　　　　　　　パラメータ：上記で編集した[統合ユーザ履歴情報
            getUiTogoUserRirekiDao().insertTogoUser(uiTogoUser);
            
        }
        //１-d、取得した[統合ユーザ履歴情報]を最初のデータ．[[発令日]]＞システム日付の場合
        else {
            //　　　　　エラー    E0201               
            //                  未来日付のユーザーデータ                
            //                  システム管理者にご連絡ください
            throw new InvalidDataException("未来日付のユーザーデータ","システム管理者にご連絡ください");
        }
        
        
        //２、[BIRDユーザ情報]を変更する。                   
        //２-a、Dao【BIRDユーザー情報．BIRDユーザー情報の取得】を実行する。
        //　　パラメータ：    パラメータ．ユーザーID                
        UIBirdUser uiBirdUser2 = getUiBirdUserDao().getBirdUser(uiBirdUser.getUserId());
        //２-b、上記で[BIRDユーザ情報]を取得できた場合、
        if (uiBirdUser2 != null ) {
            //２-b-1、パラメータ．[BIRDユーザ情報]を編集する。                 
            //　　  　パラメータ．[BIRDユーザ情報]．前回パスワード  ←　 ２-aで取得した．[BIRDユーザ情報]．パスワード
            if (!"".equals(new String(uiBirdUser.getUserPswd()))){
                uiTogoUserDto.getUiBirdUser().setLastPswd(uiBirdUser2.getUserPswd());
            }
            //パスワードの入力がない場合、現在のパスワードのまま更新する。
            else {
                uiTogoUserDto.getUiBirdUser().setUserPswd(uiBirdUser2.getUserPswd());
            }
            
            //[統合ユーザー履歴情報].使用停止フラグ ←　ブランク
            if("".equals(uiBirdUser.getTaishokuDt())) {
                uiTogoUserDto.getUiBirdUser().setStopFlg("");
            }else{
                //[統合ユーザー履歴情報].退職日が入力されている場合で、かつ、
                //退職日がシステム日付より過去の場合、ストップフラグを１にする。

                if ( isStopFlg( uiBirdUser.getTaishokuDt())) {
                    uiTogoUserDto.getUiBirdUser().setStopFlg("1");
                } else {
                    uiTogoUserDto.getUiBirdUser().setStopFlg("");
                }
                
            }
            
            //LAST系2兄弟のセット(LastTimestampをセットするとバグになるのでセットしない)
            uiTogoUserDto.getUiBirdUser().setLastUser(uiTogoUserDto.getLoginUserId());
            uiTogoUserDto.getUiBirdUser().setLastPgm(PGM_ID);
            
            //２-b-2、Dao【BIRDユーザ情報．BIRDユーザ情報の変更】を実行する。                   
            //　　    パラメータ：パラメータ．[BIRDユーザ情報]                   
            getUiBirdUserDao().updateBirdUser(uiTogoUserDto.getUiBirdUser());     
        }
        
        //３.選択された部門よりList[[所属会社情報]]を取得
        List uIUserCompanyList = getUiUserCompanyDao().getCltUserCompany(uiTogoUserDto.getInputUserId());
        List listShozoku = getCtlConvShozokuCompDao().getUserShozokuComapany(uiTogoUserDto.getUiBirdUser().getBumonCd().substring(3, 8));
        Timestamp tmsp = DateManager.getCurrentTimestamp();
        for (Iterator ite = listShozoku.iterator(); ite.hasNext();) {
            CtlConvShozokuComp entity = (CtlConvShozokuComp) ite.next();
            uiUserCompany = null;
            for (Iterator ite2 = uIUserCompanyList.iterator(); ite2.hasNext();) {
                UIUserCompany uiUserCompanyDb = (UIUserCompany) ite2.next();
                if (entity.getRCompanyCd().equals(uiUserCompanyDb.getRCompanyCd())) {
                    uiUserCompany = uiUserCompanyDb;
                    ite2.remove();
                }
            }
            if (uiUserCompany == null) {
                uiUserCompany = new UIUserCompany();
                uiUserCompany.setUserId(uiTogoUserDto.getUiBirdUser().getUserId());
                uiUserCompany.setRCompanyCd(entity.getRCompanyCd());
                uiUserCompany.setZokuseiKbn(entity.getZokuseiKbn());
                uiUserCompany.setFirstUser(uiTogoUserDto.getLoginUserId());
                uiUserCompany.setFirstPgm(PGM_ID);
                uiUserCompany.setFirstTmsp(tmsp);
                uiUserCompany.setLastUser(uiTogoUserDto.getLoginUserId());
                uiUserCompany.setLastPgm(PGM_ID);
                uiUserCompany.setLastTmsp(tmsp);
                //登録
                getUiUserCompanyDao().insertUserCompany(uiUserCompany);
            }
            else {
                uiUserCompany.setRCompanyCd(entity.getRCompanyCd());
                uiUserCompany.setZokuseiKbn(entity.getZokuseiKbn());
                uiUserCompany.setLastUser(uiTogoUserDto.getLoginUserId());
                uiUserCompany.setLastPgm(PGM_ID);
                //登録
                getUiUserCompanyDao().updateUserCompany(uiUserCompany);
            }
        }
        //DB登録済みで、更新対象にならなかったレコードを削除
        if (!uIUserCompanyList.isEmpty()) {
            for (Iterator ite = uIUserCompanyList.iterator(); ite.hasNext();) {
                UIUserCompany uiUserCompanyDb = (UIUserCompany) ite.next();
                getUiUserCompanyDao().deleteUserCompany(uiUserCompanyDb);
            }
        }

        //４、パラメータ．パスワード≠ブランクの場合、
        if (uiTogoUserDto.getInputPassword() != null) {
            //４-a、共通ロジック【パスワードの更新】を実行する。                  
            //　　パラメータ：    パラメータ．[BIRDユーザ情報]．ユーザID、                
            //            パラメータ．[BIRDユーザ情報]．パスワード、                
            //            1（変更）、              
            //            ログインユーザのユーザーID、             
            //            "BUR001"
            getUpdatePasswordLogic().execute(
                    uiTogoUserDto.getUiBirdUser().getUserId(),
                    new String(uiTogoUserDto.getUiBirdUser().getUserPswd()),
                    1,
                    uiTogoUserDto.getLoginUserId(),
                    "BUR001");
        }
        
    }

    /**
     * 参照ユーザの登録ロジック
     * @param uiTogoUserDto
     */
    private void registSanshoUser(UITogoUserDto uiTogoUserDto) {
        //１、パラメータ．[BIRDユーザ情報]．パスワード≠ブランクの場合、      
        //　　１-a、共通ロジック【パスワードの更新】を実行する。      
        //　　　　　パラメータ：    パラメータ．[BIRDユーザ情報]．ユーザID、    
        //          パラメータ．[BIRDユーザ情報]．パスワード、    
        //                      1（変更）、  
        //                      ログインユーザのユーザーID、 
        //                      "BUR001"
        if (!"".equals(new String(uiTogoUserDto.getUiBirdUser().getUserPswd()))) {
            getUpdatePasswordLogic().execute(
                    uiTogoUserDto.getUiBirdUser().getUserId(),
                    new String(uiTogoUserDto.getUiBirdUser().getUserPswd()),
                    1,
                    uiTogoUserDto.getLoginUserId(),
                    "BUR001");
        }
    }

    /**
     * 統合ユーザエンティティセットロジック
     * @param 
     */
    private UITogoUserRireki setUITogoUserRireki(UITogoUserDto uiTUDto) {
        UITogoUserRireki uiTuserNew = new UITogoUserRireki();
        UIBirdUser uiBirdUser = uiTUDto.getUiBirdUser();
        BirdDateInfo birdDateInfo = uiTUDto.getBirdDateInfo();
        UIUserCompany uiUserCompany = uiTUDto.getUiUserCompany();
        
        //[統合ユーザー履歴情報].社員番号←　パラメータ．[BIRDユーザ情報]．ユーザID
        uiTuserNew.setUserId(uiBirdUser.getUserId());
        //[統合ユーザー履歴情報].発令日 ←　システム日付
        uiTuserNew.setHatsureiDt(birdDateInfo.getSysDate());
        //[統合ユーザー履歴情報].氏名  ←　パラメータ．[BIRDユーザ情報]．ユーザ名称
        uiTuserNew.setUserNameKj(uiBirdUser.getUserNameKj());
        //[統合ユーザー履歴情報].カナ氏名←　パラメータ．[BIRDユーザ情報]．ユーザ名称(カナ)
        uiTuserNew.setUserNameKna(uiBirdUser.getUserNameKana());
        //[統合ユーザー履歴情報].アルファベット氏名   ←　ブランク
        uiTuserNew.setUserNameAlph("");
        //[統合ユーザー履歴情報].退職日 ←　パラメータ．[BIRDユーザ情報]．退職日
        uiTuserNew.setTaishokuDt(uiBirdUser.getTaishokuDt());
        //[統合ユーザー履歴情報].性別  ←　ブランク
        uiTuserNew.setSex("");
        //[統合ユーザー履歴情報].パスワード   ←　パラメータ．[BIRDユーザ情報]．パスワード
        uiTuserNew.setUserPswd(new String(uiBirdUser.getUserPswd()));
        //[統合ユーザー履歴情報].給与事業所コード←　パラメータ．[ユーザ所属会社]．会社コード(2桁)を2重で登録 
        uiTuserNew.setSalaryOfficeCd(uiUserCompany.getRCompanyCd() + uiUserCompany.getRCompanyCd());
        //[統合ユーザー履歴情報].給与事業所名称 ←　[ユーザ所属会社]．会社名称
        uiTuserNew.setSalaryOfficeName(uiUserCompany.getRCompanyName());
        //[統合ユーザー履歴情報].給与事業所歴発令日   ←　ブランク
        uiTuserNew.setSalaryOfficeDt("");
        //[統合ユーザー履歴情報].給与所属コード ←　パラメータ．[BIRDユーザ情報]．部門コード　※前0除去
        uiTuserNew.setSalaryCd(zeroSupress(uiBirdUser.getBumonCd()));
        //給与部門からランクとコードを取得する
        RankCode rankCodeSalary = getGetRankCodeLogic().execute(uiTuserNew.getSalaryCd());
        //[統合ユーザー履歴情報].給与所属部門ランク   ←　部門コードからランクを引き当てる。
        uiTuserNew.setSalarySectionRank(rankCodeSalary.getSoshikiRank());
        //[統合ユーザー履歴情報].給与所属部門コード   ←　部門コードからコードを引き当てる。
        uiTuserNew.setSalarySectionCd(rankCodeSalary.getSoshikiCd());
        //[統合ユーザー履歴情報].給与所属名称  ←　ブランク
        uiTuserNew.setSararyName(uiBirdUser.getBumonName());
        //[統合ユーザー履歴情報].給与所属歴発令日←　ブランク
        uiTuserNew.setSararyDt("");
        //[統合ユーザー履歴情報].人事事業所コード←　パラメータ．[ユーザ所属会社]．会社コード(2桁)を2重で登録 
        uiTuserNew.setJinjiOfficeCd(uiUserCompany.getRCompanyCd() + uiUserCompany.getRCompanyCd());
        //[統合ユーザー履歴情報].人事事業所名称 ←　[ユーザ所属会社]．会社名称
        uiTuserNew.setJinjiOfficeName(uiUserCompany.getRCompanyName());
        //[統合ユーザー履歴情報].人事事業所歴発令日   ←　ブランク
        uiTuserNew.setJinjiOfficeDt("");
        //[統合ユーザー履歴情報].人事所属コード   ←　パラメータ．[BIRDユーザ情報]．部門コード　※前0除去
        uiTuserNew.setJinjiCd(zeroSupress(uiBirdUser.getBumonCd()));
        //給与部門からランクとコードを取得する
        RankCode rankCodeJinji = getGetRankCodeLogic().execute(uiTuserNew.getJinjiCd());
        //[統合ユーザー履歴情報].人事所属部門ランク   ←　ブランク
        uiTuserNew.setJinjiSectionRank(rankCodeJinji.getSoshikiRank());
        //[統合ユーザー履歴情報].人事所属部門コード ←　ブランク
        uiTuserNew.setJinjiSectionCd(rankCodeJinji.getSoshikiCd());
        //[統合ユーザー履歴情報].人事所属名称  ←　ブランク
        uiTuserNew.setJinjiName(uiBirdUser.getBumonName());
        //[統合ユーザー履歴情報].兼務区分←　'0'(本務のみ)
        uiTuserNew.setKenmuKbn("0");
        //[統合ユーザー履歴情報].人事所属歴発令日←　ブランク
        uiTuserNew.setJinjiDt("");
        //[統合ユーザー履歴情報].社員区分←　ブランク
        uiTuserNew.setEmployeeKbn("");
        //[統合ユーザー履歴情報].社員区分名称  ←　ブランク
        uiTuserNew.setEmployeeName("");
        //[統合ユーザー履歴情報].社員区分歴発令日←　ブランク
        uiTuserNew.setEmployeeDt("");
        //[統合ユーザー履歴情報].役職  ←　ブランク
        uiTuserNew.setPositionCd("");
        //[統合ユーザー履歴情報].役職名称←　ブランク
        uiTuserNew.setPositionName("");
        //[統合ユーザー履歴情報].役職歴発令日  ←　ブランク
        uiTuserNew.setPositionDt("");
        //[統合ユーザー履歴情報].職務等級コード ←　ブランク
        uiTuserNew.setGradeCd("");
        //[統合ユーザー履歴情報].職務等級名称  ←　ブランク
        uiTuserNew.setGradeName("");
        //[統合ユーザー履歴情報].職級歴発令日  ←　ブランク
        uiTuserNew.setGradeDt("");
        //[統合ユーザー履歴情報].休職開始日   ←　ブランク
        uiTuserNew.setKyushokuFrom("");
        //[統合ユーザー履歴情報].休職期日←　ブランク
        uiTuserNew.setKyushokuTo("");
        //[統合ユーザー履歴情報].携帯電話番号１ ←　ブランク
        uiTuserNew.setCellularPhone1("");
        //[統合ユーザー履歴情報].携帯電話番号２ ←　ブランク
        uiTuserNew.setCellularPhone2("");
        //[統合ユーザー履歴情報].内線番号←　ブランク
        uiTuserNew.setExtensionNo("");
        //[統合ユーザー履歴情報].その他電話番号 ←　ブランク
        uiTuserNew.setOtherPhone("");
        //[統合ユーザー履歴情報].メールアドレス１←　パラメータ．[BIRDユーザ情報]．メールアドレス modify 2020/09/16 xou.zoubun
//        uiTuserNew.setMailAdd1("");
        uiTuserNew.setMailAdd1(uiTUDto.getInputMailAdd1());
        //[統合ユーザー履歴情報].メールアドレス２←　ブランク
        uiTuserNew.setMailAdd2("");
        //[統合ユーザー履歴情報].メールアドレス３←　ブランク
        uiTuserNew.setMailAdd3("");
        //[統合ユーザー履歴情報].ユーザーID（5桁）  ←　ブランク
        uiTuserNew.setOldUserId("");
        //[統合ユーザー履歴情報].使用停止フラグ ←　ブランク
        uiTuserNew.setStopFlg("");
//2009/02/24 統合ユーザのSTOP_FLGは１にしてはいけない談合により、コメントアウト。常にブランク
//        if("".equals(uiBirdUser.getTaishokuDt())) {
//            uiTuserNew.setStopFlg( "" );
//        }else{
//            //[統合ユーザー履歴情報].退職日が入力されている場合で、かつ、
//            //退職日がシステム日付より過去の場合、ストップフラグを１にする。
//
//            if (isStopFlg(uiBirdUser.getTaishokuDt())) {
//                uiTuserNew.setStopFlg("1");
//            } else {
//                uiTuserNew.setStopFlg("");
//            }
//        }
        //[統合ユーザー履歴情報].画面登録フラグ ←　'1'
        uiTuserNew.setKbnSpare1("1");
        //[統合ユーザー履歴情報].予備フラグ２  ←　ブランク
        uiTuserNew.setKbnSpare2("");
        //[統合ユーザー履歴情報].予備フラグ３  ←　ブランク
        uiTuserNew.setKbnSpare3("");
        //[統合ユーザー履歴情報].予備フラグ４  ←　ブランク
        uiTuserNew.setKbnSpare4("");
        //[統合ユーザー履歴情報].予備フラグ５  ←　ブランク
        uiTuserNew.setKbnSpare5("");
        uiTuserNew.setFirstUser(uiTUDto.getLoginUserId());
        uiTuserNew.setFirstPgm(PGM_ID);
        uiTuserNew.setFirstTmsp(DateManager.getCurrentTimestamp());
        uiTuserNew.setLastUser(uiTUDto.getLoginUserId());
        uiTuserNew.setLastPgm(PGM_ID);
//        uiTuserNew.setLastTmsp(DateManager.getCurrentTimestamp());        
        
        return uiTuserNew;
    }


    /**
     * Birdユーザエンティティセットロジック
     * @param 
     */
    private UIBirdUser setUIBirdUser(UITogoUserDto uiTogoUserDto) {
        
        UIBirdUser uiBirdUser = uiTogoUserDto.getUiBirdUser();
        //・ パラメータ．[BIRDユーザ情報].ユーザタイプコード   ← '01'(本部)
        uiBirdUser.setUsertypeCd("01");
        //・ パラメータ．[BIRDユーザ情報].パスワード更新日← システム日付
        uiBirdUser.setPswdupdtDt(uiTogoUserDto.getBirdDateInfo().getSysDate());
        //・ パラメータ．[BIRDユーザ情報].前回パスワード ← ブランク
        uiBirdUser.setLastPswd("".getBytes());
        //・ パラメータ．[BIRDユーザ情報].請求フラグ   ← '2'
        uiBirdUser.setSekyuFlg("2");
        //・ パラメータ．[BIRDユーザ情報].申込日 ← ブランク
        uiBirdUser.setAppliedDt("");
        //・ パラメータ．[BIRDユーザ情報].請求開始日   ← ブランク
        uiBirdUser.setSekyuDt("");
        //・ パラメータ．[BIRDユーザ情報].請求変更予定日 ← ブランク
        uiBirdUser.setSekyuUpdtDt("");
        //・ パラメータ．[BIRDユーザ情報].制限区分← ’1’（制限有り）
        uiBirdUser.setLimitKbn("1");
        //・ パラメータ．[BIRDユーザ情報].使用停止フラグ ← ブランク
        //[統合ユーザー履歴情報].退職日が入力されている場合で、かつ、
        //退職日がシステム日付より過去の場合、ストップフラグを１にする。
        if("".equals(uiBirdUser.getTaishokuDt())) {
            uiBirdUser.setStopFlg("");
        }else{
            uiBirdUser.setStopFlg("1");
        }

        uiBirdUser.setFirstUser(uiTogoUserDto.getLoginUserId());
        uiBirdUser.setFirstPgm(PGM_ID);
        uiBirdUser.setFirstTmsp(DateManager.getCurrentTimestamp());
        uiBirdUser.setLastUser(uiTogoUserDto.getLoginUserId());
        uiBirdUser.setLastPgm(PGM_ID);
//        uiBirdUser.setLastTmsp(DateManager.getCurrentTimestamp());
        return uiBirdUser;
    }

    /**
     * ユーザ所属エンティティセットロジック
     * @param 
     */
    private UIUserShozoku setUiUserShozoku(UITogoUserDto uiTogoUserDto) {
        UIUserShozoku uiUserShozoku = uiTogoUserDto.getUiUserShozoku();
        
        if (uiUserShozoku == null) {
            uiUserShozoku = new UIUserShozoku();
        }
        
        uiUserShozoku.setUserId(uiTogoUserDto.getUiBirdUser().getUserId());
        uiUserShozoku.setShozokuKbn("1");
        uiUserShozoku.setFirstUser(uiTogoUserDto.getLoginUserId());
        uiUserShozoku.setFirstPgm(PGM_ID);
        uiUserShozoku.setFirstTmsp(DateManager.getCurrentTimestamp());
        uiUserShozoku.setLastUser(uiTogoUserDto.getLoginUserId());
        uiUserShozoku.setLastPgm(PGM_ID);
//        uiUserShozoku.setLastTmsp(DateManager.getCurrentTimestamp());
        
        return uiUserShozoku;
    }

    
    /**
     * 0サプレスロジック
     * @param convBefore ：頭の0を除去する文字列
     * @return convAfter ：頭の0を除去した文字列
     */
    private String zeroSupress(String convBefore) {
        String convAfter = new String();
        convAfter = convBefore;
        
        for (int i=0; i<convBefore.length(); i++) {
            if("0".equals(convBefore.substring(i,i+1).toString())) {
                convAfter = convAfter.substring(1);
            }else{
                break;
            }
        }
        
        return convAfter;
    }

    
    

    /**
     * StopFlgをONにするかチェック
     * 
     * 引数で渡ってくる日付(退職日)をシステム日付と比較し、
     * 退職日＜＝システム日付の場合、TRUEを返す。
     * 
     * @param String taishokuDate ：退職日
     * @return boolean ストップフラグを立てる場合TRUEを返す。
     */
    private boolean isStopFlg(String taishokuDate) {
        if ( getSysDate().compareTo(taishokuDate) >= 0 ){
            return true;
        }
        return false;
    }

    
    public UITogoUserRirekiDao getUiTogoUserRirekiDao() {
        return uiTogoUserRirekiDao;
    }

    public void setUiTogoUserRirekiDao(UITogoUserRirekiDao uiTogoUserRirekiDao) {
        this.uiTogoUserRirekiDao = uiTogoUserRirekiDao;
    }

    public UIBirdUserDao getUiBirdUserDao() {
        return uiBirdUserDao;
    }

    public void setUiBirdUserDao(UIBirdUserDao uiBirdUserDao) {
        this.uiBirdUserDao = uiBirdUserDao;
    }

    public UIUserCompanyDao getUiUserCompanyDao() {
        return uiUserCompanyDao;
    }

    public void setUiUserCompany(UIUserCompanyDao uiUserCompany) {
        this.uiUserCompanyDao = uiUserCompany;
    }

    public UIUserShozokuDao getUiUserShozokuDao() {
        return uiUserShozokuDao;
    }

    public void setUiUserShozokuDao(UIUserShozokuDao uiUserShozokuDao) {
        this.uiUserShozokuDao = uiUserShozokuDao;
    }

    public UpdatePasswordLogic getUpdatePasswordLogic() {
        return updatePasswordLogic;
    }

    public void setUpdatePasswordLogic(UpdatePasswordLogic updatePasswordLogic) {
        this.updatePasswordLogic = updatePasswordLogic;
    }

    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public GetRankCodeLogic getGetRankCodeLogic() {
        return getRankCodeLogic;
    }

    public void setGetRankCodeLogic(GetRankCodeLogic getRankCodeLogic) {
        this.getRankCodeLogic = getRankCodeLogic;
    }

	/**
	 * @return ctlConvShozokuCompDao を戻します。
	 */
	public CtlConvShozokuCompDao getCtlConvShozokuCompDao() {
		return ctlConvShozokuCompDao;
	}

	/**
	 * @param ctlConvShozokuCompDao を クラス変数ctlConvShozokuCompDaoへ設定します。
	 */
	public void setCtlConvShozokuCompDao(CtlConvShozokuCompDao ctlConvShozokuCompDao) {
		this.ctlConvShozokuCompDao = ctlConvShozokuCompDao;
	}


}