/**
 * 
 */
package jp.co.isid.mos.bird.sysadmin.userregist.util;

import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.sysadmin.userregist.dto.UserRegistDto;

/**
 * 作成日:2010/03/19
 * @author xkinu
 *
 */
public class UserRegistUtil {
    /**
     * 日付フォーマット (YYYYMMDD型)
     * @param userRegistDto
     */
    public static void formatNoSlash(UserRegistDto userRegistDto) {

        DateFormatter dateFormatter = new DateFormatter(
                DateFormatter.DATE_TYPE_YMD, DateFormatter.PATTERN_SLASH);
        userRegistDto.getUIUser().setSeikyuDt(dateFormatter.format(userRegistDto.getUIUser()
                .getSeikyuDt(), DateFormatter.PATTERN_NORMAL,
                DateFormatter.DATE_TYPE_YMD));
        userRegistDto.getUIUser().setSeikyuUpdtDt(dateFormatter.format(userRegistDto.getUIUser()
                .getSeikyuUpdtDt(), DateFormatter.PATTERN_NORMAL,
                DateFormatter.DATE_TYPE_YMD));
        userRegistDto.getUIUser().setAppliedDt(dateFormatter.format(userRegistDto.getUIUser()
                .getAppliedDt(), DateFormatter.PATTERN_NORMAL,
                DateFormatter.DATE_TYPE_YMD));
    }

}
