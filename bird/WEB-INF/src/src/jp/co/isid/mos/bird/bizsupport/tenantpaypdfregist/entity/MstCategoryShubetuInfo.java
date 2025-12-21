/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.entity;

/**
 * カテゴリー種別情報エンティティ
 * 
 * 作成日:2009/07/06
 * @author xkinu
 *
 */
public class MstCategoryShubetuInfo {
    public static final String TABLE = "BC28KEIS";
    
    public static final String keiCategory_COLUMN = "KEI_CATEGORY";

    public static final String keiShu_COLUMN = "KEI_SHU";

    public static final String keiShuName_COLUMN = "KEI_SHU_NAME";

    public static final String saveDir_COLUMN = "SAVE_DIR";

    public static final String dispMonths_COLUMN = "DISP_MONTHS";

    public static final String soatKey_COLUMN = "SOAT_KEY";

    private String keiCategory;
    
    private String keiShu;
    
    private String keiShuName;
    
    private String saveDir;
    
    private String dispMonths;
    
    private String soatKey;

	public String getKeiCategory() {
		return keiCategory;
	}

	public void setKeiCategory(String keiCategory) {
		this.keiCategory = keiCategory;
	}

	public String getKeiShu() {
		return keiShu;
	}

	public void setKeiShu(String keiShu) {
		this.keiShu = keiShu;
	}

	public String getKeiShuName() {
		return keiShuName;
	}

	public void setKeiShuName(String keiShuName) {
		this.keiShuName = keiShuName;
	}

	public String getSaveDir() {
		return saveDir;
	}

	public void setSaveDir(String saveDir) {
		this.saveDir = saveDir;
	}

	public String getSoatKey() {
		return soatKey;
	}

	public void setSoatKey(String seq) {
		this.soatKey = seq;
	}

	public String getDispMonths() {
		return dispMonths;
	}

	public void setDispMonths(String dispMonths) {
		this.dispMonths = dispMonths;
	}
    
    
}
