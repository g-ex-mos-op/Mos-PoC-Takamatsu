/*
 * 作成日: 2006/01/25
 *
 */
package jp.co.isid.mos.bird.inforegist.contactregist.logic.impl;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.framework.exception.GenericCommentException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.Verifier;
import jp.co.isid.mos.bird.inforegist.contactregist.dto.ContactRegistDto;
import jp.co.isid.mos.bird.inforegist.contactregist.logic.CheckRenrakuLogic;

/**
 * @author xyuchida
 *
 */
public class CheckRenrakuLogicImpl implements CheckRenrakuLogic {

    public static final String LOGIC_ID = "BIF001L02";

    private Verifier dateVerifier;

    public Verifier getDateVerifier() {
        return dateVerifier;
    }

    public void setDateVerifier(Verifier dateVerifier) {
        this.dateVerifier = dateVerifier;
    }

    public void execute(ContactRegistDto contactRegistDto, PublicTargetDto publicTargetDto) {

        // タイトル
        String title = contactRegistDto.getTitle();
        if (title == null || title.length() <= 0) {
            throw new NotNullException("タイトル", "contactTitle", null);
        }
        if (title.getBytes().length > 80) {
            throw new InvalidInputException("タイトル", "contactTitle", null);
        }

// add start xkhata 20070627 タイトルチェック        
        DefaultJapaneseVerifier defJapanese = new DefaultJapaneseVerifier();
        if ( !defJapanese.validate(title)) {
            throw new InvalidInputException("タイトル", "contactTitle", null); 
        }        
// add end
        
        // 発行日
        String pubDate = contactRegistDto.getPubDate();
        if (pubDate == null || pubDate.length() <= 0) {
            throw new NotNullException("公開開始日",  "contactpubDate", null);
        }
        if (!dateVerifier.validate(pubDate)) {
            throw new InvalidInputException("公開開始日", "contactpubDate", null);
        }

        // カテゴリID
        String cateId = contactRegistDto.getCateId();
        if (cateId == null || cateId.length() <= 0) {
            throw new InvalidInputException("カテゴリー", "contactcate", null);
        }

        // メッセージ
        String message = contactRegistDto.getMessage();
        if (message == null || message.length() <= 0) {
            throw new NotNullException("メッセージ", "contactmsg", null);
        }
        if (message.getBytes().length > 800) {
            throw new InvalidInputException("メッセージ", "contactmsg", null);
        }
        
        DefaultJapaneseVerifier dj = new DefaultJapaneseVerifier();
// add start xkahta 20070627
        if (!dj.validate(message)) {
            throw new InvalidInputException("メッセージ", "contactmsg", null);
        }
// add end
        // 公開対象
        if (publicTargetDto.getListTrnControlCompanySize() <= 0
                && publicTargetDto.getListTrnControlShozokuSize() <= 0
                && publicTargetDto.getListTrnControlGyotaiSize() <= 0
                && publicTargetDto.getListTrnControlGyotaiKobetuSize() <= 0
                && publicTargetDto.getListTrnControlGyotaiTenpoSize() <= 0) {
            throw new InvalidInputException("公開対象");
        }
        
        //添付ファイル名 レングスチェック
        String attachFl1 = contactRegistDto.getAttachFl1();
        if (attachFl1 != null) {
            if (attachFl1.getBytes().length > 60) {
                throw new GenericCommentException("ファイル名（添付ファイル１）は全角３０文字まで",  "uploadTenpuFile", null);
            }
        }
        String attachFl2 = contactRegistDto.getAttachFl2();
        if (attachFl2 != null) {
            if (attachFl2.getBytes().length > 60) {
                throw new GenericCommentException("ファイル名（添付ファイル２）は全角３０文字まで", "uploadTenpuFile", null);
            }
        }
        String attachFl3 = contactRegistDto.getAttachFl3();
        if (attachFl3 != null) {
            if (attachFl3.getBytes().length > 60) {
                throw new GenericCommentException("ファイル名（添付ファイル３）は全角３０文字まで", "uploadTenpuFile", null);
            }
        }
    }
}
