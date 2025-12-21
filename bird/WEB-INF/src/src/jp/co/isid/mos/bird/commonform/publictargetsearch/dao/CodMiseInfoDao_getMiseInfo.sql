select
    BM01.COMPANY_CD, 
    BM01.MISE_CD,
    BM01.MISE_NAME_KJ
from 
    BM01TENM BM01
where 
      BM01.MISE_GRP1=/*gyotaiKbn*/'010'
AND   BM01.MISE_CD in /*miseCd*/('02001', '01753')
AND	  BM01.COMPANY_CD=/*companyCd*/'00' 