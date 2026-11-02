# ACI – Custom Items + Texturen + Rezepte (Paper 1.21.4+)

## Bauen
Java 21 + Maven: `mvn package` → `target/ACI-1.0.0.jar` in den `plugins/`-Ordner legen.

## Befehle
- `/aci` – Admin-Menü (Items erstellen, Textur, Name, Lore, Effekte, Rezepte)
- `/aci give <id> [spieler] [anzahl]`, `/aci list`, `/aci reload`, `/aci pack`
- `/ci` – Rezeptbuch für alle: Übersicht, jedes Rezept hat eine eigene Seite (Pfeile zum Blättern)

## Resource Pack
Das Plugin baut `plugins/ACI/resourcepack.zip` selbst (7 Kristalle eingebaut) und hostet es per
Mini-Webserver. In `config.yml` eintragen:
- `resource-pack.host.public-address`: öffentliche IP/Domain deines Servers
- `resource-pack.host.port` (Standard 8123, TCP freigeben)

Eigene Texturen: PNG (quadratisch) in `plugins/ACI/textures/` legen → im `/aci`-Menü
„Pack neu laden“ klicken → im Item-Editor unter „Textur wählen“ auswählen.

## Effekte
Im Item-Editor → Effekte:
- Linksklick Stufe +1, Rechtsklick Slot wechseln, Shift-Links Dauer wechseln, Shift-Rechts entfernen
- Dauer „solange getragen“ = nur aktiv, solange das Item im Slot ist
- Dauer > 0 (z. B. 8:00 min) = Effekt mit dieser Länge, wird erneuert solange das Item im Slot ist

Standard: Gelb = Stärke II (Nebenhand), Blau = Schnelligkeit II (Nebenhand),
Orange = Feuerresistenz (Nebenhand, 8:00 min).
