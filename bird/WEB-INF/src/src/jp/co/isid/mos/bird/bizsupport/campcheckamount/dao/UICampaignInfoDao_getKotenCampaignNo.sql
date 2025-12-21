select
	min(rtrim(BM53.CMP_NO)) as CMP_NO
from
	BM53CPJK as BM53
where
	BM53.JU_MST_FLG = '1' and
	BM53.POS_FROM_DT <= /*posStrDt*/'' and
    ( BM53.CMP_FROM >= /*cmpStrDt*/''  or  BM53.POS_END_DT >= /*posEndDt*/'' )
group by BM53.CMP_FROM
order by BM53.CMP_FROM desc, CMP_NO
FETCH FIRST 1 ROWS ONLY