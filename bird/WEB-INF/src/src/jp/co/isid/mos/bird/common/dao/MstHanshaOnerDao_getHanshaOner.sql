select 
  COMPANY_CD,
  ONER_CD,
  FIRST_USER,
  FIRST_PGM,
  FIRST_TMSP,
  LAST_USER,
  LAST_PGM,
  LAST_TMSP 
from
  BM59HONR BM59 
where 
  COMPANY_CD = /*companyCd*/'00' and
  ONER_CD = /*onerCd*/'37001' 