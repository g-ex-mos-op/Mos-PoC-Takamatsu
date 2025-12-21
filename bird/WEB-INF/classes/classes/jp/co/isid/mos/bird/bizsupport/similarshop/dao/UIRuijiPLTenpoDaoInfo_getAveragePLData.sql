select
	'' as MISE_CD,
	'' as MISE_NAME_KJ,
	(case when avg(BT17.URIAGEDAKA) is null
	      then 0 else avg(BT17.URIAGEDAKA) end) as URIAGEDAKA,
	(case when avg(BT17.URIAGEGENKA) is null
	      then 0 else avg(BT17.URIAGEGENKA) end) as URIAGEGENKA,
	(case when avg(BT17.URIAGE_SO_RIEKI) is null
	      then 0 else avg(BT17.URIAGE_SO_RIEKI) end) as URIAGE_SO_RIEKI,
	(case when avg(BT17.SALARY) is null
	      then 0 else avg(BT17.SALARY) end) as SALARY,
	(case when avg(BT17.YACHIN) is null
	      then 0 else avg(BT17.YACHIN) end) as YACHIN,
	(case when avg(BT17.SUIKOU_HI) is null
	      then 0 else avg(BT17.SUIKOU_HI) end) as SUIKOU_HI,
	(case when avg(BT17.ROYALTY) is null
	      then 0 else avg(BT17.ROYALTY) end) as ROYALTY,
	(case when avg(BT17.TESURYO) is null
	      then 0 else avg(BT17.TESURYO) end) as TESURYO,
	(case when avg(BT17.KOUKOKU) is null
	      then 0 else avg(BT17.KOUKOKU) end) as KOUKOKU,
	(case when avg(BT17.SHOUMOU) is null
	      then 0 else avg(BT17.SHOUMOU) end) as SHOUMOU,
	(case when avg(BT17.HOUTEI_FUKURI) is null
	      then 0 else avg(BT17.HOUTEI_FUKURI) end) as HOUTEI_FUKURI,
	(case when avg(BT17.FUKURI_KOUSEI) is null
	      then 0 else avg(BT17.FUKURI_KOUSEI) end) as FUKURI_KOUSEI,
	(case when avg(BT17.KOUSAI) is null
	      then 0 else avg(BT17.KOUSAI) end) as KOUSAI,
	(case when avg(BT17.RYOHI) is null
	      then 0 else avg(BT17.RYOHI) end) as RYOHI,
	(case when avg(BT17.TUSIN) is null
	      then 0 else avg(BT17.TUSIN) end) as TUSIN,
	(case when avg(BT17.LEASE) is null
	      then 0 else avg(BT17.LEASE) end) as LEASE,
	(case when avg(BT17.SHARYO) is null
	      then 0 else avg(BT17.SHARYO) end) as SHARYO,
	(case when avg(BT17.SOZEI) is null
	      then 0 else avg(BT17.SOZEI) end) as SOZEI,
	(case when avg(BT17.HOKEN) is null
	      then 0 else avg(BT17.HOKEN) end) as HOKEN,
	(case when avg(BT17.UNCHIN) is null
	      then 0 else avg(BT17.UNCHIN) end) as UNCHIN,
	(case when avg(BT17.SHUZEN) is null
	      then 0 else avg(BT17.SHUZEN) end) as SHUZEN,
	(case when avg(BT17.YOBI) is null
	      then 0 else avg(BT17.YOBI) end) as YOBI,
	(case when avg(BT17.ZAPPI) is null
	      then 0 else avg(BT17.ZAPPI) end) as ZAPPI,
	(case when avg(BT17.KEIHI_SHOKEI) is null
	      then 0 else avg(BT17.KEIHI_SHOKEI) end) as KEIHI_SHOKEI,
	(case when avg(BT17.SHOKYAKU_RIEKI) is null
	      then 0 else avg(BT17.SHOKYAKU_RIEKI) end) as SHOKYAKU_RIEKI,
	(case when avg(BT17.GENKA_SHOKYAKU) is null
	      then 0 else avg(BT17.GENKA_SHOKYAKU) end) as GENKA_SHOKYAKU,
	(case when avg(BT17.EIGAI_SHUEKI) is null
	      then 0 else avg(BT17.EIGAI_SHUEKI) end) as EIGAI_SHUEKI,
	(case when avg(BT17.EIGAI_HIYO) is null
	      then 0 else avg(BT17.EIGAI_HIYO) end) as EIGAI_HIYO,
	(case when avg(BT17.HONSHAHI_HAI) is null
	      then 0 else avg(BT17.HONSHAHI_HAI) end) as HONSHAHI_HAI,
	(case when avg(BT17.RIEKI) is null
	      then 0 else avg(BT17.RIEKI) end) as RIEKI,
	(case when avg(BT17.URIAGE) is null
	      then 0 else avg(BT17.URIAGE) end) as URIAGE,
	(case when avg(BT17.BUPPAN) is null
	      then 0 else avg(BT17.BUPPAN) end) as BUPPAN,
	(case when avg(BT17.ELEC) is null
	      then 0 else avg(BT17.ELEC) end) as ELEC,
	(case when avg(BT17.GAS) is null
	      then 0 else avg(BT17.GAS) end) as GAS,
	(case when avg(BT17.WATER) is null
	      then 0 else avg(BT17.WATER) end) as WATER,
	(case when avg(BT17.SONOTA) is null
	      then 0 else avg(BT17.SONOTA) end) as SONOTA,
	(case when avg(BT17.GENZAIRYO_KEI) is null
	      then 0 else avg(BT17.GENZAIRYO_KEI) end) as GENZAIRYO_KEI,
	(case when avg(BT17.GENZAIRYO_SHIRE) is null
	      then 0 else avg(BT17.GENZAIRYO_SHIRE) end) as GENZAIRYO_SHIRE,
	(case when avg(BT17.GENZAIRYO_ZAIKO) is null
	      then 0 else avg(BT17.GENZAIRYO_ZAIKO) end) as GENZAIRYO_ZAIKO,
	(case when avg(BT17.YASAI_KEI) is null
	      then 0 else avg(BT17.YASAI_KEI) end) as YASAI_KEI,
	(case when avg(BT17.YASAI_SHIRE) is null
	      then 0 else avg(BT17.YASAI_SHIRE) end) as YASAI_SHIRE,
	(case when avg(BT17.YASAI_ZAIKO) is null
	      then 0 else avg(BT17.YASAI_ZAIKO) end) as YASAI_ZAIKO,
	(case when avg(BT17.HOUZAI_KEI) is null
	      then 0 else avg(BT17.HOUZAI_KEI) end) as HOUZAI_KEI,
	(case when avg(BT17.HOUZAI_SHIRE) is null
	      then 0 else avg(BT17.HOUZAI_SHIRE) end) as HOUZAI_SHIRE,
	(case when avg(BT17.HOUZAI_ZAIKO) is null
	      then 0 else avg(BT17.HOUZAI_ZAIKO) end) as HOUZAI_ZAIKO,
	(case when avg(BT17.BUPPAN_KEI) is null
	      then 0 else avg(BT17.BUPPAN_KEI) end) as BUPPAN_KEI,
	(case when avg(BT17.BUPPAN_SHIRE) is null
	      then 0 else avg(BT17.BUPPAN_SHIRE) end) as BUPPAN_SHIRE,
	(case when avg(BT17.BUPPAN_ZAIKO) is null
	      then 0 else avg(BT17.BUPPAN_ZAIKO) end) as BUPPAN_ZAIKO,
	(case when avg(BT17.YAKUIN_SALARY) is null
	      then 0 else avg(BT17.YAKUIN_SALARY) end) as YAKUIN_SALARY,
	(case when avg(BT17.YAKUIN_BONUS) is null
	      then 0 else avg(BT17.YAKUIN_BONUS) end) as YAKUIN_BONUS,
	(case when avg(BT17.YAKUIN_RETIRE) is null
	      then 0 else avg(BT17.YAKUIN_RETIRE) end) as YAKUIN_RETIRE,
	(case when avg(BT17.YAKUIN_KEI) is null
	      then 0 else avg(BT17.YAKUIN_KEI) end) as YAKUIN_KEI,
	(case when avg(BT17.SALARY_SALARY) is null
	      then 0 else avg(BT17.SALARY_SALARY) end) as SALARY_SALARY,
	(case when avg(BT17.SALARY_BONUS) is null
	      then 0 else avg(BT17.SALARY_BONUS) end) as SALARY_BONUS,
	(case when avg(BT17.SALARY_RETIRE) is null
	      then 0 else avg(BT17.SALARY_RETIRE) end) as SALARY_RETIRE,
	(case when avg(BT17.SALARY_KEI) is null
	      then 0 else avg(BT17.SALARY_KEI) end) as SALARY_KEI,
	(case when avg(BT17.ZAKKYU_SALARY) is null
	      then 0 else avg(BT17.ZAKKYU_SALARY) end) as ZAKKYU_SALARY,
	(case when avg(BT17.ZAKKYU_BONUS) is null
	      then 0 else avg(BT17.ZAKKYU_BONUS) end) as ZAKKYU_BONUS,
	(case when avg(BT17.ZAKKYU_RETIRE) is null
	      then 0 else avg(BT17.ZAKKYU_RETIRE) end) as ZAKKYU_RETIRE,
	(case when avg(BT17.ZAKKYU_KEI) is null
	      then 0 else avg(BT17.ZAKKYU_KEI) end) as ZAKKYU_KEI,
	(case when avg(BT17.BONUS_KEI) is null
	      then 0 else avg(BT17.BONUS_KEI) end) as BONUS_KEI,
	(case when avg(BT17.RETIRE_KEI) is null
	      then 0 else avg(BT17.RETIRE_KEI) end) as RETIRE_KEI,
	(case when avg(BT17.KASHIIRE_UP) is null
	      then 0 else avg(BT17.KASHIIRE_UP) end) as KASHIIRE_UP,
	(case when avg(BT17.KASHIIRE_DOWN) is null
	      then 0 else avg(BT17.KASHIIRE_DOWN) end) as KASHIIRE_DOWN,
	(case when avg(BT17.KASHIIRE_ZANDAKA) is null
	      then 0 else avg(BT17.KASHIIRE_ZANDAKA) end) as KASHIIRE_ZANDAKA,
	(case when avg(BT17.KAPPU_UP) is null
	      then 0 else avg(BT17.KAPPU_UP) end) as KAPPU_UP,
	(case when avg(BT17.KAPPU_DOWN) is null
	      then 0 else avg(BT17.KAPPU_DOWN) end) as KAPPU_DOWN,
	(case when avg(BT17.KAPPU_ZANDAKA) is null
	      then 0 else avg(BT17.KAPPU_ZANDAKA) end) as KAPPU_ZANDAKA,
	(case when avg(BT17.LEASE_UP) is null
	      then 0 else avg(BT17.LEASE_UP) end) as LEASE_UP,
	(case when avg(BT17.LEASE_DOWN) is null
	      then 0 else avg(BT17.LEASE_DOWN) end) as LEASE_DOWN,
	(case when avg(BT17.LEASE_ZANDAKA) is null
	      then 0 else avg(BT17.LEASE_ZANDAKA) end) as LEASE_ZANDAKA
		
from
	BT17PLDT BT17,
	BM01TENM BM01

where
	BM01.MISE_CD = BT17.MISE_CD and
	BM01.COMPANY_CD = BT17.COMPANY_CD and
	BM01.COMPANY_CD = '00' and
	BT17.PL_YM = /*zenMonthYYYYMM*/'200601' and
	BT17.ERR_FLG = '0' and
	
/*IF areaDai != null && areaDai != "" */
    BM01.AREA_DAI = /*areaDai*/'031' and
/*END*/

/*IF miseKeitai != "" && miseKeitai != null */
    BM01.MISE_KEITAI = /*miseKeitai*/'001' and
/*END*/
	
/*IF locateKbn != "" && locateKbn != null */
    BM01.LOCATE_KBN = /*locateKbn*/'001' and
/*END*/

/*IF uriageSitei != "" */
    BT17.URIAGEDAKA BETWEEN /*uriMin*/0 AND /*uriMax*/999999999 and
/*END*/

/*IF openDtMin != "" && openDtMin != null */
    BM01.OPEN_DT BETWEEN /*openDtMin*/'19950401' AND /*openDtMax*/'20060331' and
/*END*/

	BT17.PL_TYPE = '1'
	