select COMPANY_CD,
       MISE_CD,
       MISE_NAME_KJ,
       ONER_CD,
       CLOSE_DT

from   BM01TENM

where  COMPANY_CD = /*companyCd*/'00' AND
       ONER_CD = /*onerCd*/'36478' AND
       CLOSE_DT > /*sysDate*/'20070110'
 
order by MISE_CD