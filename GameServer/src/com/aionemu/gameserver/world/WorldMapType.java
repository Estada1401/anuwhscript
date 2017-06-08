package com.aionemu.gameserver.world;

public enum WorldMapType
{
	//Asmodea
	PANDAEMONIUM(120010000),
	MARCHUTAN(120020000),
	MARCHUTAN_PRIORY(120080000),
	FATEBOUND_ABBEY(140010000),
	ISHALGEN(220010000),
	MORHEIM(220020000),
	ALTGARD(220030000),
	BELUSLAN(220040000),
	BRUSTHONIN(220050000),
	NORSVOLD(220110000),
	TOWER_OF_ETERNITY_A(220120000),
	PERNON(710010000),
	
	//Elysea
	SANCTUM(110010000),
	KAISINEL(110020000),
	KAISINEL_ACADEMY(110070000),
	WISPLIGHT_ABBEY(130090000),
	POETA(210010000),
	ELTNEN(210020000),
	VERTERON(210030000),
	HEIRON(210040000),
	THEOBOMOS(210060000),
	ILUMA(210100000),
	TOWER_OF_ETERNITY_E(210110000),
	ORIEL(700010000),
	LIVE_PARTY_CONCERT_ALL(600080000),
	
	//Balaurea
	INGGISON(210050000),
	CYGNEA(210070000),
	GRIFFOEN(210080000),
	IDIAN_DEPTHS_L(210090000),
	GELKMAROS(220070000),
	ENSHAR(220080000),
	HABROK(220090000),
	IDIAN_DEPTHS_D(220100000),
	SILENTERA_CANYON(600010000),
	KALDOR(600090000),
	LEVINSHOR(600100000),
	
	//Abyss.
	RESHANTA(400010000),
	
	//Panesterra.
	BELUS(400020000),
	TRANSIDIUM_ANNEX(400030000),
	ASPIDA(400040000),
	ATANATOS(400050000),
	DISILLON(400060000),
	
	//Prison
	DE_PRISON(510010000),
	DF_PRISON(520010000),
	
	//Instances
	NO_ZONE_NAME(300010000),
	ID_TEST_DUNGEON(300020000),
	NOCHSANA_TRAINING_CAMP(300030000),
	DARK_POETA(300040000),
	CARPUS_ISLE_STONEROOM(300050000),
	SULFUR_TREE_NEST(300060000),
	HAMATE_ISLE_STONEROOM(300070000),
	LEFT_WING_CHAMBER(300080000),
	RIGHT_WING_CHAMBER(300090000),
	STEEL_RAKE(300100000),
	DREDGION(300110000),
	GRAVE_OF_STEEL_STONEROOM(300120000),
	TWILIGHT_BATTLEFIELD_STONEROOM(300130000),
	ISLE_OF_ROOTS_STONEROOM(300140000),
	UDAS_TEMPLE(300150000),
	UDAS_TEMPLE_LOWER(300160000),
	BESHMUNDIR_TEMPLE(300170000),
	TALOCS_HOLLOW(300190000),
	HARAMEL(300200000),
	DREDGION_OF_CHANTRA(300210000),
	KROMEDE_TRIAL(300230000),
	KARAMATIS_A(310010000),
	KARAMATIS_B(310020000),
	AERDINA(310030000),
	GERANAIA(310040000),
	AETHEROGENETICS_LAB(310050000),
	FRAGMENT_OF_DARKNESS(310060000),
	SLIVER_OF_DARKNESS(310070000),
	SANCTUM_UNDERGROUND_ARENA(310080000),
	TRINIEL_UNDERGROUND_ARENA(320090000),
	INDRATU_FORTRESS(310090000),
	AZOTURAN_FORTRESS(310100000),
	THEOBOMOS_LAB(310110000),
	ATAXIAR_A(310120000),
	ATAXIAR_B(320010000),
	ATAXIAR_C(320020000),
	BREGIRUN(320030000),
	NIDALBER(320040000),
	ARKANIS_TEMPLE(320050000),
	SPACE_OF_OBLIVION(320060000),
	SPACE_OF_DESTINY(320070000),
	DRAUPNIR_CAVE(320080000),
	FIRE_TEMPLE(320100000),
	ALQUIMIA_RESEARCH_CENTER(320110000),
	SHADOW_COURT_DUNGEON(320120000),
	ADMA_STRONGHOLD(320130000),
	ATAXIAR_D(320140000),
	
	// Maps 2.5
	ESOTERRACE(300250000),
	EMPYREAN_CRUCIBLE(300300000),
	CRUCIBLE_CHALLENGE(300320000),
	
	// Maps 2.7
	ARENA_OF_CHAOS(300350000),
	ARENA_OF_DISCIPLINE(300360000),
	CHAOS_TRAINING_GROUNDS(300420000),
	DISCIPLINE_TRAINING_GROUNDS(300430000),
	PADMARASHKA_CAVE(320150000),
	
	//Instance 3.0
	ATURAM_SKY_FORTRESS(300240000),
	RENTUS_BASE(300280000),
	STEEL_RAKE_CABIN(300460000),
	TERATH_DREDGION(300440000),
	
	//Map 3.9/4.3
	ARENA_OF_HARMONY(300450000),
	SEALED_DANUAR_MYSTICARIUM(300480000),
	TIAMAT_STRONGHOLD(300510000),
	DRAGON_LORD_REFUGE(300520000),
	ETERNAL_BASTION(300540000),
	ARENA_OF_GLORY(300550000),
	SHUGO_IMPERIAL_TOMB(300560000),
	HARMONY_TRAINING_GROUND(300570000),
	OPHIDAN_BRIDGE(300590000),
	THE_HEXWAY(300700000),
	INFINITY_SHARD(300800000),
	UNITY_TRAINING_GROUNDS(301100000),
	DANUAR_RELIQUARY(301110000),
	KAMAR_BATTLEFIELD(301120000),
	SAURO_SUPPLY_BASE(301130000),
	SEIZED_DANUAR_SANCTUARY(301140000),
	ASTERIA_IU_SOLO(301150000),
	NIGHTMARE_CIRCUS(301160000),
    RUKIBUKI_CIRCUS_TROUPE_CAMP(301200000),
	ENGULFED_OPHIDAN_BRIDGE(301210000),
	IRON_WALL_WARFRONT(301220000),
	ILLUMINARY_OBELISK(301230000),
	LUCKY_OPHIDAN_BRIDGE(301320000),
	LUCKY_DANUAR_RELIQUARY(301330000),
	
	//Hidden Kysis-Miren-Krotan Barracks 4.9.1
	HIDDEN_KYSIS_BARRACKS(301240000),
	HIDDEN_MIREN_BARRACKS(301250000),
    HIDDEN_KROTAN_BARRACKS(301260000),
	
	//Kysis-Miren-Krotan Barracks
	KYSIS_BARRACKS(301280000),
	MIREN_BARRACKS(301290000),
	KROTAN_BARRACKS(301300000),
	
	//Map 4.7
	LINKGATE_FOUNDRY(301270000),
	IDGEL_DOME(301310000),
	LINKGATE_FOUNDRY_Q(301340000),
	LEGION_LINKGATE_FOUNDRY(301350000),
	INFERNAL_DANUAR_RELIQUARY(301360000),
	INFERNAL_ILLUMINARY_OBELISK(301370000),
	
	//Map 4.7.5/4.8
	RAKSANG_RUINS(300610000),
	OCCUPIED_RENTUS_BASE(300620000),
	ANGUISHED_DRAGON_LORD_REFUGE(300630000),
	DANUAR_SANCTUARY(301380000),
	DRAKENSPIRE_DEPTHS(301390000),
	THE_SHUGO_EMPEROR_VAULT(301400000),
	STONESPEAR_REACH(301500000),
	
	//Map 4.9
	SEALED_ARGENT_MANOR(301510000),
	DRAKENSPIRE_DEPTHS_Q(301520000),
	EMPEROR_TRILLIRUNERK_SAFE(301590000),
	
	//Map 5.0
	ARCHIVES_OF_ETERNITY(301540000),
	ARCHIVES_OF_ETERNITY_Q(301570000),
	SANCTUARY_DUNGEON(301580000),
	ADMA_FALL(301600000),
	THEOBOMOS_TEST_CHAMBER(301610000),
	DRAKENSEER_LAIR(301620000),
	
	//Map 5.0.5
	CONTAMINED_UNDERPATH(301630000),
	SECRET_MUNITIONS_FACTORY(301640000),
	
	//Map 5.1
	CRADLE_OF_ETERNITY(301550000),
	IDETERNITY_03(301560000),
	ASHUNATAL_DREDGION(301650000),
	FALLEN_POETA(301660000),
	OPHIDAN_WARPATH(301670000),
	IDGEL_DOME_LANDMARK(301680000),
	AETHER_MINE(301690000),
	SMOLDERING_FIRE_TEMPLE(302000000),
	FISSURE_OF_OBLIVION(302100000),
	
	//Housing
	HOUSING_LC_LEGION(700020000, true),
	HOUSING_DC_LEGION(710020000, true),
	ORIEL_STUDIO(720010000, true),
	PERNON_STUDIO(730010000, true),
	
	//Test Map
	TEST_BASIC(900020000),
	TEST_SERVER(900030000),
	TEST_GIANTMONSTER(900100000),
	HOUSING_BARRACK(900110000),
	TEST_EMPYREAN_CRUCIBLE(900120000),
	IDLDF5RE_TEST(900130000),
	TEST_KGW(900140000),
	TEST_BASIC_MJ(900150000),
	KAMAR_TEST(900160000),
	TEST_INTRO(900170000),
	TEST_SERVER_ART(900180000),
	TEST_TAG_MATCH(900190000),
	TEST_TIME_ATTACK(900200000),
	TOURNAMENT_STAGE_TEST(900210000),
	SYSTEM_BASIC(900220000),
	TOURNAMENT_LOBBY_TEST(900230000);
	
	private final int worldId;
	private final boolean isPersonal;
	
	WorldMapType(int worldId) {
		this(worldId, false);
	}
	
	WorldMapType(int worldId, boolean personal) {
		this.worldId = worldId;
		this.isPersonal = personal;
	}
	
	public int getId() {
		return worldId;
	}
	
	public boolean isPersonal() {
		return isPersonal;
	}
	
	public static WorldMapType getWorld(int id) {
		for (WorldMapType type : WorldMapType.values()) {
			if (type.getId() == id) {
				return type;
			}
		}
		return null;
	}
}