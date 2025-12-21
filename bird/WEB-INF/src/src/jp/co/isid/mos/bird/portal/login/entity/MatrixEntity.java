package jp.co.isid.mos.bird.portal.login.entity;

import java.util.Hashtable;

public class MatrixEntity {

    /*
     * 2桁の16進数を格納したHashtable
     * キーは「1」～「5」 （2006/01/06現在）
     */
//    private Hashtable _key = new Hashtable();
//    private String keyA;
//    private String keyB;
//    private String keyC;
//    private String keyD;
//    private String keyE;
    private String lastUpdtDt;
    private Hashtable hash = new Hashtable();
    private Hashtable hashButtonLabel = new Hashtable();
    
    /**
     * @return 
     */
    public String getKeyA() {
        return (String) hash.get("A");
    }
    
    /**
     * @return 
     */
    public String getKeyB() {
        return (String) hash.get("B");
    }
    
    /**
     * @return 
     */
    public String getKeyC() {
        return (String) hash.get("C");
    }
    
    /**
     * @return 
     */
    public String getKeyD() {
        return (String) hash.get("D");
    }
    
    /**
     * @return 
     */
    public String getKeyE() {
        return (String) hash.get("E");
    }
    
    /**
     * @return 
     */
    public String getButtonLabelA() {
        return (String) hashButtonLabel.get("A");
    }
    
    /**
     * @return 
     */
    public String getButtonLabelB() {
        return (String) hashButtonLabel.get("B");
    }
    
    /**
     * @return 
     */
    public String getButtonLabelC() {
        return (String) hashButtonLabel.get("C");
    }
    
    /**
     * @return 
     */
    public String getButtonLabelD() {
        return (String) hashButtonLabel.get("D");
    }
    
    /**
     * @return 
     */
    public String getButtonLabelE() {
        return (String) hashButtonLabel.get("E");
    }
    
    public void setValue(int i, String val) {
        String[] keys = {"A", "B", "C", "D", "E"};
        hash.put(keys[i], val);
    }
    
    public void setButtonLabel(int i, String val) {
        String[] keys = {"A", "B", "C", "D", "E"};
        hashButtonLabel.put(keys[i], val);
    }
    
    public String getValue(String key) {
        Object ret = hash.get(key);
        if (ret == null) {
            ret = "";
        }
        return ((String) ret).trim();
    }

    /**
     * @param lastUpdtDt lastUpdtDt を設定。
     */
    public void setLastUpdtDt(String lastUpdtDt) {
        this.lastUpdtDt = lastUpdtDt;
    }

    /**
     * @return lastUpdtDt を戻します。
     */
    public String getLastUpdtDt() {
        return lastUpdtDt;
    }
}
