select distinct  
    BM04.COMPANY_CD
,   BM04.GYOTAI_KBN 
,   BC09.GYOTAI_KBN_NAME 
from 
    BM04GTCP BM04 
,   BC09GTAI BC09        
where    
      BM04.GYOTAI_KBN=BC09.GYOTAI_KBN 
AND   BM04.COMPANY_CD 
      in (
         select distinct 
             COMPANY_CD 
         from 
              BC06COMM 
         where 
              R_COMPANY_CD in /*companyCd*/('01', '02')
         )
