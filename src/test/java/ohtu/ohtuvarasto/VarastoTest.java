package ohtu.ohtuvarasto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudenVarastonTilavuusEiVoiOllaNegatiivinen() {
        Varasto varasto2 = new Varasto(-10);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuusMyosKuormitetullaKonstruktorilla() {
        Varasto varasto2 = new Varasto(10, 1.0);
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaTilavuusEiNegatiivinenMyoskaanKuormitetullaKonstruktorilla() {
        Varasto varasto2 = new Varasto(-10, 1.0);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaAlkusaldoEiNegatiivinenKuormitetullaKonstruktorilla() {
        Varasto varasto2 = new Varasto(10, -1.0);
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void varastoonEiVoiLisataNegatiivistaMaaraa() {
        varasto.lisaaVarastoon(-1.0);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void tayteenVarastoonEiVoiLisata() {
        varasto.lisaaVarastoon(10.0);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
        varasto.lisaaVarastoon(5.0);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastostaEiVoiOttaaNegatiivistaMaaraa() {
        varasto.otaVarastosta(-1.0);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastostaEiVoiOttaaMaaraansaEnempaa() {
        varasto.lisaaVarastoon(5.0);
        varasto.otaVarastosta(5.0);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
        varasto.otaVarastosta(5.0);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void merkkijonoesitysOnOikein() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

}