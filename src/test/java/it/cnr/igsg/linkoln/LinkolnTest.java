package it.cnr.igsg.linkoln;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import it.cnr.igsg.linkoln.entity.AnnotationEntity;
import org.junit.jupiter.api.Test;

record City(String name, String linkolnCode) {
}

public class LinkolnTest {

    private static final List<City> CITIES_TO_BE_TESTED = Arrays.asList(
            new City("agrigento", "AG"),
            new City("alessandria", "AL"),
            new City("ancona", "AN"),
            new City("aosta", "AO"),
            new City("aquila", "AQ"),
            new City("arezzo", "AR"),
            new City("ascoli piceno", "AP"),
            new City("asti", "AT"),
            new City("avellino", "AV"),
            new City("avezzano", "A515"),
            new City("barcellona pozzo di gotto", "M228"),
            new City("bari", "BA"),
            new City("belluno", "BL"),
            new City("benevento", "BN"),
            new City("bergamo", "BG"),
            new City("biella", "BI"),
            new City("bologna", "BO"),
            new City("bolzano", "BZ"),
            new City("brescia", "BS"),
            new City("brindisi", "BR"),
            new City("busto arsizio", "B300"),
            new City("cagliari", "CA"),
            new City("caltagirone", "B429"),
            new City("caltanissetta", "CL"),
            new City("campobasso", "CB"),
            new City("cassino", "C034"),
            new City("castrovillari", "C349"),
            new City("catania", "CT"),
            new City("catanzaro", "CZ"),
            new City("chieti", "CH"),
            new City("civitavecchia", "C773"),
            new City("como", "CO"),
            new City("cosenza", "CS"),
            new City("cremona", "CR"),
            new City("crotone", "KR"),
            new City("cuneo", "CN"),
            new City("enna", "EN"),
            new City("fermo", "FM"),
            new City("ferrara", "FE"),
            new City("firenze", "FI"),
            new City("foggia", "FG"),
            new City("forli", "D704"),
            new City("forli'", "D704"),
            new City("forlì", "D704"),
            new City("frosinone", "FR"),
            new City("gela", "D960"),
            new City("genova", "GE"),
            new City("gorizia", "GO"),
            new City("grosseto", "GR"),
            new City("imperia", "IM"),
            new City("isernia", "IS"),
            new City("ivrea", "E379"),
            new City("l'aquila", "AQ"),
            new City("la spezia", "SP"),
            new City("lagonegro", "E409"),
            new City("lamezia terme", "M208"),
            new City("lanciano", "E435"),
            new City("lanusei", "E447"),
            new City("larino", "E456"),
            new City("latina", "LT"),
            new City("lecce", "LE"),
            new City("lecco", "LC"),
            new City("livorno", "LI"),
            new City("locri", "D976"),
            new City("lodi", "LO"),
            new City("lucca", "LU"),
            new City("macerata", "MC"),
            new City("mantova", "MN"),
            new City("marsala", "E974"),
            new City("massa", "F023"),
            new City("matera", "MT"),
            new City("messina", "ME"),
            new City("milano", "MI"),
            new City("modena", "MO"),
            new City("monza", "MB"),
            new City("napoli nord", "NN"),
            new City("napoli", "NA"),
            new City("nocera inferiore", "F912"),
            new City("nola", "F924"),
            new City("novara", "NO"),
            new City("nuoro", "NU"),
            new City("oristano", "OR"),
            new City("padova", "PD"),
            new City("palermo", "PA"),
            new City("palmi", "G288"),
            new City("paola", "G317"),
            new City("parma", "PR"),
            new City("patti", "G377"),
            new City("pavia", "PV"),
            new City("perugia", "PG"),
            new City("pesaro", "G479"),
            new City("pescara", "PE"),
            new City("piacenza", "PC"),
            new City("pisa", "PI"),
            new City("pistoia", "PT"),
            new City("pordenone", "PN"),
            new City("potenza", "PZ"),
            new City("prato", "PO"),
            new City("ragusa", "RG"),
            new City("ravenna", "RA"),
            new City("reggio calabria", "RC"),
            new City("reggio di calabria", "RC"),
            new City("reggio emilia", "RE"),
            new City("rieti", "RI"),
            new City("rimini", "RN"),
            new City("roma", "RM"),
            new City("rovereto", "H612"),
            new City("rovigo", "RO"),
            new City("salerno", "SA"),
            new City("santa maria capua vetere", "I234"),
            new City("sassari", "SS"),
            new City("savona", "SV"),
            new City("sciacca", "I523"),
            new City("siena", "SI"),
            new City("siracusa", "SR"),
            new City("sondrio", "SO"),
            new City("spoleto", "I921"),
            new City("sulmona", "I804"),
            new City("taranto", "TA"),
            new City("tempio pausania", "L093"),
            new City("teramo", "TE"),
            new City("termini imerese", "L112"),
            new City("terni", "TR"),
            new City("tivoli", "L182"),
            new City("torino", "TO"),
            new City("torre annunziata", "L245"),
            new City("trani", "L328"),
            new City("trapani", "TP"),
            new City("trento", "TN"),
            new City("treviso", "TV"),
            new City("trieste", "TS"),
            new City("udine", "UD"),
            new City("urbino", "L500"),
            new City("vallo della lucania", "L628"),
            new City("varese", "VA"),
            new City("vasto", "E372"),
            new City("velletri", "L722"),
            new City("venezia", "VE"),
            new City("verbania", "L746"),
            new City("vercelli", "VC"),
            new City("verona", "VR"),
            new City("vibo valentia", "VV"),
            new City("vicenza", "VI"),
            new City("viterbo", "VT")
    );

    private static String toString(Collection<AnnotationEntity> entities) {
        return entities.stream()
                .map(e -> "[" + e.getEntityName() + ":" + e.getValue() + ":"
                        + e.getPosition() + ":" + e.getText().length() + "] \"" + e.getText() + "\"")
                .collect(Collectors.joining(", "));
    }

    @Test
    public void shouldParseAllTrialCourts() {
        String trialCourtPattern = "tribunale %s";
        CITIES_TO_BE_TESTED.forEach(city -> {
            String trialCourt = format(trialCourtPattern, city.name());
            LinkolnDocument document = Linkoln.run(trialCourt);
            String expectedAnnotation = format("""
                            [CITY:IT_%s:11:%d] "%s", [CL_AUTH:IT_TRB_%s:1:%d] "tribunale %s\"""",
                    city.linkolnCode(),
                    city.name().length(),
                    city.name(),
                    city.linkolnCode(),
                    trialCourt.length(),
                    city.name());
            String actualAnnotation = toString(document.getAnnotationEntities());
            assertEquals(expectedAnnotation, actualAnnotation);
        });
    }

}
