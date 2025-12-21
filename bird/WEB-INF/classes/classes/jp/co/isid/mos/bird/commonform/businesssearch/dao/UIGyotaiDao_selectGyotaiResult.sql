select
    BC09GTAI.GYOTAI_KBN,
    BC09GTAI.GYOTAI_KBN_NAME
from
    BC09GTAI
where
    BC09GTAI.GYOTAI_KBN in /*gyotaiKbnList*/('000', '010')
order by
    BC09GTAI.GYOTAI_KBN
