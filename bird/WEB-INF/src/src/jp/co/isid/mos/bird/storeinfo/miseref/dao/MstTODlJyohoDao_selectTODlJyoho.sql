select
      rtrim(BM75.HIKITUGI_DT_OPEN) as HIKITUGI_DATE,
      rtrim(BM01.OPEN_DT) as OPEN_DATE,
      rtrim(BM01.CLOSE_DT) as CLOSE_DATE,
      rtrim(BM75.MISE_CD) as MISE_CD,
      rtrim(BM01.MISE_NAME_KJ) as MISE_NAME,
      rtrim(BM75.MISE_CD_SHINTEN) as MISE_CD_SHINTEN,
      rtrim(BM75.MISE_CD_SAISHIN) as MISE_CD_SAISHIN,
      rtrim(BM75.MISE_CD_MOTO) as MISE_CD_MOTO,
      rtrim(BM75.MISE_CD_SAKI) as MISE_CD_SAKI,
      rtrim(BM75.ONER_CD) as ONER_CD,
      rtrim(BM11.ONER_NAME_KJ) as ONER_NAME
from
      BM75MKRI BM75
      inner join BM01TENM BM01
        on BM75.COMPANY_CD = BM01.COMPANY_CD
        and BM75.MISE_CD = BM01.MISE_CD
      left join BM11ONER BM11
        on BM75.COMPANY_CD = BM11.COMPANY_CD
        and BM75.ONER_CD = BM11.ONER_CD
      inner join (
           select MISE_CD_SAISHIN
           from BM75MKRI
           where COMPANY_CD = /*condCompanyCd*/'00'
           and MISE_CD = /*condMiseCd*/'99999'
      ) BM75_1
      on BM75.MISE_CD_SAISHIN = BM75_1.MISE_CD_SAISHIN
where
       BM75.COMPANY_CD = /*condCompanyCd*/'00'
order by
       HIKITUGI_DATE desc