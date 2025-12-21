select
   count(*)
from
  BD61TSES bd61
    left outer join VIR51USER vir51 on ( bd61.user_id=vir51.user_id )
    left outer join VBM78COMR vbm78 on ( bd61.kb_company_cd=vbm78.kb_company_cd  )
    left outer join BM79RETI  bm79  on ( bd61.retire_cd=bm79.retire_cd )
where
    1=1
    /*IF nendoFrom != null */
      AND bd61.nendo >= /*nendoFrom*/'2013'
   /*END*/
   /*IF nendoTo != null */
      AND bd61.nendo <= /*nendoTo*/'2017'
   /*END*/
   /*IF userID != null */
      AND bd61.user_id = /*userID*/'00081018'
   /*END*/
   /*IF !kbCompanyCd.equals("") */
      AND bd61.kb_company_cd = /*kbCompanyCd*/'00'
   /*END*/
