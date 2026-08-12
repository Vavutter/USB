from pathlib import Path
root = Path('makerx-project')
main = root/'app/src/main/java/com/moritz/vescfix03/MainActivity.java'
s = main.read_text()
repls = {
    'Makerx konnte die Oberfläche nicht vollständig starten.':'VESC Legal konnte die Oberfläche nicht vollständig starten.',
    'TextView brand = Ui.text(this, "Makerx", 30, Ui.TEXT, true);':'TextView brand = Ui.text(this, "MAKERX", 28, Ui.TEXT, true);',
    'Eine gültige Makerx-Firmwareantwort bestätigt die Makerx-Verbindung.':'Eine gültige VESC-Firmwareantwort bestätigt die VESC-Verbindung.',
    'Kein Makerx verbunden':'Kein VESC verbunden',
    'Live-Werte werden erst nach einer gültigen Makerx-Protokollantwort angezeigt.':'Live-Werte werden erst nach einer gültigen VESC-Protokollantwort angezeigt.',
    'Auf Makerx anwenden':'Auf VESC anwenden',
    'Makerx nicht verbunden':'VESC nicht verbunden',
    'Separat programmierte Makerx-Controller (1–4)':'Separat programmierte VESC-Controller (1–4)',
    'Das native Makerx-Wattlimit wird ebenfalls geschrieben.':'Das native VESC-Wattlimit wird ebenfalls geschrieben.',
    'Diese Werte sind in UI, Domain-Logik und Makerx-Schreibschicht erzwungen.':'Diese Werte sind in UI, Domain-Logik und VESC-Schreibschicht erzwungen.',
    'Der aktuelle Makerx-Zustand bleibt unverändert.':'Der aktuelle VESC-Zustand bleibt unverändert.',
    'Profil auf Makerx schreiben?':'Profil auf VESC schreiben?',
    'return "Makerx verbunden";':'return "VESC verbunden";',
    'return "BLE verbunden · Makerx-Prüfung";':'return "BLE verbunden · VESC-Prüfung";',
    'text = "Makerx " + ble.getFirmwareText();':'text = "VESC " + ble.getFirmwareText();',
    'profiles.add(new Profile("Legal Max", 25.0, 600));':'profiles.add(new Profile("Sport", 25.0, 600));',
}
for a,b in repls.items():
    s=s.replace(a,b)
# Migrate already stored default profile name on load.
needle='Profile p = new Profile(o.optString("name", "Profil"), o.optDouble("speed", 15.0), o.optInt("watts", 250));\n                    profiles.add(p);'
if needle in s:
    s=s.replace(needle,'Profile p = new Profile(o.optString("name", "Profil"), o.optDouble("speed", 15.0), o.optInt("watts", 250));\n                    if ("Legal Max".equals(p.name)) p.name = "Sport";\n                    profiles.add(p);')
# Remove the old operating warning card if present; keep the fixed-limits card.
old_title='safety.addView(Ui.text(this, "Wichtiger Betriebshinweis", 20, Ui.WARNING, true));'
old_text='Die App ersetzt keine mechanische Geschwindigkeitsbegrenzung, Betriebserlaubnis oder fachgerechte VESC-Konfiguration. Vor Änderungen Rad frei aufbocken, Eingangssignal deaktivieren, Sicherung verwenden und die zurückgelesenen Werte prüfen.'
s=s.replace(old_title,'safety.addView(Ui.text(this, "Feste Limits", 20, Ui.WARNING, true));')
s=s.replace(old_text,'Maximal 25 km/h Zielgeschwindigkeit und maximal 600 W elektrische Leistung. Diese Werte sind in UI, Domain-Logik und VESC-Schreibschicht erzwungen.')
# Flatter header/navigation proportions.
s=s.replace('header.setBackgroundColor(Color.argb(185, 6, 18, 38));','header.setBackgroundColor(Ui.NAVY);')
s=s.replace('header.setMinimumHeight(Ui.dp(this, 108));','header.setMinimumHeight(Ui.dp(this, 88));')
s=s.replace('brand.setLetterSpacing(0.04f);','brand.setLetterSpacing(0.02f);')
s=s.replace('nav.setBackgroundColor(Color.argb(245, 6, 18, 37));','nav.setBackgroundColor(Ui.NAVY);')
s=s.replace('nav.setMinimumHeight(Ui.dp(this, 74));','nav.setMinimumHeight(Ui.dp(this, 68));')
s=s.replace('bg.setCornerRadius(Ui.dp(this, 16));','bg.setCornerRadius(Ui.dp(this, 10));')
main.write_text(s)

ble=root/'app/src/main/java/com/moritz/vescfix03/BleManager.java'
t=ble.read_text().replace('Makerx Controller','VESC-kompatibler Controller').replace('Makerx wird abgefragt','VESC wird abgefragt').replace('Makerx antwortet nicht','VESC antwortet nicht').replace('mit dem Makerx verbinden','mit dem VESC verbinden').replace('Am Makerx wurde','Am VESC wurde').replace('Makerx " + getFirmwareText() + " verbunden.','VESC " + getFirmwareText() + " verbunden.')
ble.write_text(t)
proto=root/'app/src/main/java/com/moritz/vescfix03/VescProtocol.java'
proto.write_text(proto.read_text().replace('Ungültiger Makerx-Datenrahmen','Ungültiger VESC-Datenrahmen'))
# Flat UI implementation.
(root/'app/src/main/java/com/moritz/vescfix03/Ui.java').write_text(Path('makerx111/Ui.java').read_text())
# New package/version so broken older builds cannot interfere.
b=root/'app/build.gradle'
t=b.read_text().replace('applicationId "com.moritz.makerx109"','applicationId "com.moritz.makerx111"').replace('versionCode 10','versionCode 12').replace('versionName "1.0.9"','versionName "1.1.1"')
b.write_text(t)
