package jp.co.isid.mos.bird.inforegist.notificationregist.logic.impl;

import jp.co.isid.mos.bird.common.code.InfoShu;
import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.BusinessRuleException;
import jp.co.isid.mos.bird.framework.exception.GenericCommentException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;

import jp.co.isid.mos.bird.inforegist.notificationregist.dto.RegistFormDto;
import jp.co.isid.mos.bird.inforegist.notificationregist.entity.UITutatuInfo;
import jp.co.isid.mos.bird.inforegist.notificationregist.logic.CheckTutatuLogic;

public class CheckTutatuLogicImpl implements CheckTutatuLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BIF002L03";
    
    
    
    /**
     * 既存の文書情報一覧の取得
     * @param RegistFormDto 編集画面DTO
     * @param PublicTargetDto 公開対象共通DTO
     * @exception ApplicationException
     */
    public void checkTutatu(RegistFormDto dto, PublicTargetDto publicTargetDto) throws ApplicationException {
            UITutatuInfo entity = dto.getEditEntity();
        /* １．必須チェック */
       validate(dto);
    
       /* ２．ファイルの登録チェック */
        // 通達ファイルのファイル名レングスチェック
//---2006/03/23 結合テスト課題管理表No507対応
        if (!isNull(entity.getFileName())) {
            if(entity.getFileName().getBytes().length > 60){
                throw new GenericCommentException("ファイル名は全角３０文字まで");
            }
        }
//---2006/03/23 end
        // 添付タイトル１
        // 添付ファイル１オブジェクト
      if (!isNull(entity.getAttachName1())) {
          if(entity.getAttachName1().getBytes().length > 80){
              throw new BusinessRuleException("添付タイトル１文字数制限");
          }

          if (isNull(entity.getAttachFl1())) {
               throw new NotNullException("添付ファイルタイトル１を指定場合、添付ファイル１");
          }else{
//--- 2006/03/23 結合テスト課題管理表No507対応
//          if(entity.getAttachFl1().getBytes().length > 30){
//              throw new BusinessRuleException("添付ファイル１文字数制限");
//          }
          	if(entity.getAttachFl1().getBytes().length > 60){
              	throw new GenericCommentException("ファイル名は全角３０文字まで", "（添付ファイル１）");
            }
          }
       }
        // 添付タイトル２
        // 添付ファイル２オブジェクト
      if (!isNull(entity.getAttachName2())) {
          if(entity.getAttachName2().getBytes().length > 80){
              throw new BusinessRuleException("添付タイトル２文字数制限");
          }
          if (isNull(entity.getAttachFl2())) {
               throw new NotNullException("添付ファイルタイトル２を指定場合、添付ファイル２");
          }else{
//--- 2006/03/23 結合テスト課題管理表No507対応
//              if(entity.getAttachFl2().getBytes().length > 30){
//                  throw new BusinessRuleException("添付ファイル２文字数制限");
//              }
              if(entity.getAttachFl2().getBytes().length > 60){
                throw new GenericCommentException("ファイル名は全角３０文字まで", "（添付ファイル２）");
            }
          }
       }
        // 添付タイトル３
        // 添付ファイル３オブジェクト
      if (!isNull(entity.getAttachName3())) {
          if(entity.getAttachName3().getBytes().length > 80){
              throw new BusinessRuleException("添付タイトル３文字数制限");
          }

         if (isNull(entity.getAttachFl3())) {
               throw new NotNullException("添付ファイルタイトル３を指定場合、添付ファイル３");
         }else{
//--- 2006/03/23 結合テスト課題管理表No507対応
//             if(entity.getAttachFl3().getBytes().length > 30){
//                 throw new BusinessRuleException("添付ファイル３文字数制限");
//             }
             if(entity.getAttachFl3().getBytes().length > 60){
                throw new GenericCommentException("ファイル名は全角３０文字まで", "（添付ファイル３）");
            }
         }
       }
        
        /* ３．公開対象の選択チェック */
      if (publicTargetDto.getListTrnControlCompanySize() <= 0
               && publicTargetDto.getListTrnControlShozokuSize() <= 0
               && publicTargetDto.getListTrnControlGyotaiSize() <= 0
               && publicTargetDto.getListTrnControlGyotaiKobetuSize() <= 0
               && publicTargetDto.getListTrnControlGyotaiTenpoSize() <= 0) {
           throw new InvalidInputException("公開対象");
       }
      
      
      
      
      
    }
    
    /**
     * 事前条件
     * @param RegistFormDto dto
     * @throws ApplicationException
     */
     private void validate(RegistFormDto dto) throws ApplicationException {
          UITutatuInfo entity = dto.getEditEntity();
        
          // タイトル(必須)
          if (isNull(entity.getTitle())) {
              throw new NotNullException("タイトル", "titlename", null);
          }
          if(entity.getTitle().getBytes().length > 80) {
              throw new InvalidInputException("タイトル", "titlename", null);
          }
// xkhata 20070627 add start
          DefaultJapaneseVerifier def = new DefaultJapaneseVerifier();
          if ( !def.validate(entity.getTitle())) {
              throw new InvalidInputException("タイトル", "titlename", null); 
          }        
// xkhata 20070627 add end
          // サブタイトル
          String subtitle = entity.getSubTitle();
          if (!isNull(subtitle)) {
          	if(subtitle.getBytes().length > 80) {
          		throw new InvalidInputException("サブタイトル", "subtitle", null);
          	}
//          xkhata 20070627 add start
            DefaultJapaneseVerifier dj = new DefaultJapaneseVerifier();
            if ( !dj.validate(subtitle)) {
                throw new InvalidInputException("サブタイトル", "subtitle", null); 
            }        
//   xkhata 20070627 add end
            
          }
          // ファイル登録(必須)
//          if (dto.getUploadedMainFile() == null && isNull(entity.getFileName())) {
          if (dto.isAddMainFile()) {
              throw new NotNullException("ファイル登録", "uploadFile", null);
          }
          // 通達管理No
          String kanriNo = entity.getKanriNo();
// 2006/03/16 xkinu start DELETE 任意対応
/*
          if (isNull(kanriNo)) {
              throw new NotNullException("通達管理No", "kanriNo", null);
          }
*/
          if (!isNull(kanriNo)) {
          	if(kanriNo.getBytes().length > 24) {
          		throw new InvalidInputException(InfoShu.TUTATU_NAME + "管理No", "kanriNo", null);
          	}
          }
// 2006/03/16 xkinu end DELETE 任意対応
          // カテゴリID(必須)
          if (isNull(entity.getCateId())) {
              throw new NotNullException("カテゴリID", "cateId", null);
          }
	      // 発行日(必須)
	      String pubDate = entity.getPubDate();
          if (isNull(pubDate)) {
            throw new NotNullException("公開開始日", "pubDate", null);
          }
          DateVerifier dateVerifier = new DateVerifier(DateVerifier.DATE_TYPE_YMD);
          if (!dateVerifier.validate(pubDate)) {
              throw new InvalidInputException("公開開始日", "pubDate", null);
          }
    }
    
    
    private boolean isNull(String val) {
        if (val == null) {
            return true;
        }
        if ("".equals(val.trim())) {
            return true;
        }
        return false;
    }
}
