update BT22ENKN
set
	STAFF_ID = /*uiEntryState.staffId*/'36478001',
	ONER_CD = /*uiEntryState.onerCd*/'36478',
	EMP_EXP_YEAR = /*uiEntryState.empExpYear*/'1',
	EMP_EXP_MONTH = /*uiEntryState.empExpMonth*/'2',
	PA_EXP_YEAR = /*uiEntryState.paExpYear*/'3',
	PA_EXP_MONTH = /*uiEntryState.paExpMonth*/'4',
	JOB = /*uiEntryState.job*/'êEà ',
	GUIDE_KBN = /*uiEntryState.guideKbn*/'OTHER',
	GUIDE_NAME = /*uiEntryState.guideName*/'éÅñº',
	GUIDE_ZIP = /*uiEntryState.guideZip*/'111-9999',
	GUIDE_ADRS1 = /*uiEntryState.guideAdrs1*/'èZèäÇP',
	GUIDE_ADRS2 = /*uiEntryState.guideAdrs2*/'èZèäÇQ',
	GUIDE_ADRS3 = /*uiEntryState.guideAdrs3*/'èZèäÇR',
	OTHER_FLG1 = /*uiEntryState.otherFlg1*/'1',
	OTHER_FLG2 = /*uiEntryState.otherFlg2*/'2',
	OTHER_FLG3 = /*uiEntryState.otherFlg3*/'3',
	BOSS_NAME = /*uiEntryState.bossName*/'è„éiñº',
	BOSS_GROUP = /*uiEntryState.bossGroup*/'è„éièäëÆ',
	BOSS_JOB_TYPE = /*uiEntryState.bossJobType*/'è„éiêEà ',
	BOSS_COMMENT = /*uiEntryState.bossComment*/'è„éiÉRÉÅÉìÉg',
	LAST_USER = /*uiEntryState.lastUser*/'99990003',
	LAST_PGM = /*uiEntryState.lastPgm*/'sql_test',
	LAST_TMSP = current timestamp 
where
	ENTRY_CD = /*uiEntryState.entryCd*/'30'
and ENTRY_KAI = /*uiEntryState.entryKai*/'003'
and ENTRY_YEAR = /*uiEntryState.entryYear*/'2006'
and STAFF_ID = /*uiEntryState.selectedStaffId*/'36478001'