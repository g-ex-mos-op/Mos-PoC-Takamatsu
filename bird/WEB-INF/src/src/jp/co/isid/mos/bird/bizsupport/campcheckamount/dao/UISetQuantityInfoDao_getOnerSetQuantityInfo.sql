select
	SUB2.CNT,
	SUB1.MISE_CD,
	SUB1.MISE_NAME_KJ,
	SUB1.KOTEI_CD,
	SUB1.KOTEI_CD_NAME,
	SUB1.KOTEI_AMT,
	SUB1.SIBU_CD,
	rtrim(SUB1.SIBU_NAME) as SIBU_NAME
from
	(select
		rtrim(CM07.MISE_CD) as MISE_CD,
		rtrim(BM01.MISE_NAME_KJ) as MISE_NAME_KJ,
		rtrim(CM07.KOTEI_CD) as KOTEI_CD,
		rtrim(CM06.KOTEI_CD_NAME) as KOTEI_CD_NAME,
		CM07.KOTEI_AMT,
		rtrim(BM01.SIBU_CD)  as SIBU_CD,
		(
			select
				BM10.SIBU_NAME
			from BM10GSIB BM10
			where
				BM01.SIBU_CD = BM10.SIBU_CD
				and BM01.COMPANY_CD = BM10.COMPANY_CD
		)  as SIBU_NAME
	from
		CM06KOTE as CM06,
		CM07KAMT as CM07,
		BM01TENM as BM01
	where
	    CM07.COMPANY_CD = /*companyCd*/'00' and
	    CM07.COMPANY_CD = BM01.COMPANY_CD and
		CM07.MISE_CD = BM01.MISE_CD and
		BM01.OPEN_DT <= /*sysDate*/'20060823' and
		BM01.CLOSE_DT >= /*sysDate*/'20060823' and
		CM07.KOTEI_CD = CM06.KOTEI_CD and
		CM07.MISE_CD in (
			SELECT
			    MISE_CD
			FROM
		    	BM01TENM as BM01_2
			WHERE
		    	COMPANY_CD = /*companyCd*/'00' and
		    	ONER_CD in /*onerCdList*/('36961','36478')
				and CLOSE_DT > /*sysDate*/'200605'
			ORDER BY MISE_CD)
	order by MISE_CD, KOTEI_CD) as SUB1,
	(select
		count(CM07.MISE_CD) as CNT,
		rtrim(CM07.MISE_CD) as MISE_CD
	from
		CM06KOTE as CM06,
		CM07KAMT as CM07,
		BM01TENM as BM01
	where
	    CM07.COMPANY_CD = /*companyCd*/'00' and
	    CM07.COMPANY_CD = BM01.COMPANY_CD and
		CM07.MISE_CD = BM01.MISE_CD and
		BM01.OPEN_DT <= /*sysDate*/'20060823' and
		BM01.CLOSE_DT >= /*sysDate*/'20060823' and
		CM07.KOTEI_CD = CM06.KOTEI_CD and
		CM07.MISE_CD in (
			SELECT
			    MISE_CD
			FROM
		    	BM01TENM as BM01_2
			WHERE
		    	COMPANY_CD = /*companyCd*/'00' and
		    	ONER_CD in /*onerCdList*/('36961','36478')
				and CLOSE_DT > /*sysDate*/'200605'
			ORDER BY MISE_CD)
	group by CM07.MISE_CD
	order by MISE_CD) as SUB2
where
	SUB1.MISE_CD = SUB2.MISE_CD
order by
/*IF downloadFlag*/
SUB1.SIBU_CD,
/*END*/
SUB1.MISE_CD, SUB1.KOTEI_CD