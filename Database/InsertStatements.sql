-- Beispiel-Inserts für Person-Tabelle
INSERT INTO digitalisierungAusbildungsnachweis.Person (username, email, passwordHash, vorname, nachname, rolle)
VALUES
('maxmus1', 'max.mustermann@example.com', 'hashed_password_1', 'Max', 'Mustermann', 3),
('annmus1', 'anna.musterfrau@example.com', 'hashed_password_2', 'Anna', 'Musterfrau', 3),
('petsch1', 'peter.schmidt@example.com', 'hashed_password_3', 'Peter', 'Schmidt', 3),
('lismei1', 'lisa.meier@example.com', 'hashed_password_4', 'Lisa', 'Meier', 3),
('marmue1', 'martin.mueller@example.com', 'hashed_password_5', 'Martin', 'Mueller', 3),
('sarsch1', 'sarah.schulz@example.com', 'hashed_password_6', 'Sarah', 'Schulz', 3),
('karwol7', 'karl.wolfgang@example.com', 'hashed_password_7', 'Karl', 'Wolfgang', 3),
('jasbau8', 'jasmin.bauer@example.com', 'hashed_password_8', 'Jasmin', 'Bauer', 2),
('timkoc9', 'tim.koch@example.com', 'hashed_password_9', 'Tim', 'Koch', 2),
('stesch10', 'stefanie.schmidt@example.com', 'hashed_password_10', 'Stefanie', 'Schmidt', 1);


-- Beispiel-Inserts für Eintrag-Tabelle
INSERT INTO digitalisierungAusbildungsnachweis.Eintrag (ausgefuerteArbeit, einzelStunden, gesamtStunden, abteilung)
VALUES
('Softwareschreiben 1\nSoftwareschreiben 2', '2\n6', 8, 'IT'),
('Projektmanagement\nDatenbankdesign', '3\n5', 8, 'Verwaltung'),
('Datenbankdesign\nKundenbetreuung', '4\n4', 8, 'IT'),
('Kundenbetreuung\nSoftwareschreiben', '2\n6', 8, 'Kundenservice'),
('Produktentwicklung\nProjektmanagement', '1\n7', 8, 'Entwicklung'),
('Softwareschreiben 1\nSoftwareschreiben 2', '2\n6', 8, 'IT'),
('Projektmanagement\nDatenbankdesign', '3\n5', 8, 'IT'),
('Datenbankdesign\nKundenbetreuung', '4\n4', 8, 'IT'),
('Kundenbetreuung\nSoftwareschreiben', '2\n6', 8, 'IT'),
('Produktentwicklung\nProjektmanagement', '1\n7', 8, 'IT'),
('Softwareschreiben 1\nSoftwareschreiben 2', '2\n6', 8, 'IT'),
('Projektmanagement\nDatenbankdesign', '3\n5', 8, 'Verwaltung'),
('Datenbankdesign\nKundenbetreuung', '4\n4', 8, 'IT'),
('Kundenbetreuung\nSoftwareschreiben', '2\n6', 8, 'Kundenservice'),
('Produktentwicklung\nProjektmanagement', '1\n7', 8, 'Entwicklung'),
('Softwareschreiben 1\nSoftwareschreiben 2', '2\n6', 8, 'IT'),
('Projektmanagement\nDatenbankdesign', '3\n5', 8, 'Verwaltung'),
('Datenbankdesign\nKundenbetreuung', '4\n4', 8, 'IT'),
('Kundenbetreuung\nSoftwareschreiben', '2\n6', 8, 'Kundenservice'),
('Produktentwicklung\nProjektmanagement', '1\n7', 8, 'Entwicklung');

-- Beispiel-Inserts für Ausbildung-Tabelle
INSERT INTO digitalisierungAusbildungsnachweis.Ausbildung (auszubildenerId, ausbilderId, beruf, startZeitpunkt, endZeitpunkt)
VALUES
(1, 8, 'Softwareentwicklung', '2023-01-01', '2026-01-01'),
(2, 8, 'Projektmanagement', '2023-02-01', '2026-02-01'),
(3, 8, 'Datenbankadministration', '2023-03-01', '2026-03-01'),
(4, 8, 'Softwareentwicklung', '2023-04-01', '2026-04-01'),
(5, 9, 'Projektmanagement', '2023-05-01', '2026-05-01'),
(6, 9, 'Softwareentwicklung', '2023-01-01', '2026-01-01'),
(7, 9, 'Projektmanagement', '2023-02-01', '2026-02-01');

-- Beispiel-Inserts für Nachweis-Tabelle
INSERT INTO digitalisierungAusbildungsnachweis.Nachweis (auszubildenerId, ggfAusbildungsAbteilung, startAusbildungswoche, eintragMontagId, eintagDienstagId, eintagMittwochId, eintagDonnerstagId, eintragFreitagId)
VALUES
(1, 'Abteilung1', '2024-01-01', 1, 6, 3, 4, 5),
(1, 'Abeilung2', '2024-01-01', 1, 2, 3, 4, 5),
(1, 'Abeilung2','2024-01-01', 1, 2, 3, 4, 5),
(1, 'Abteilung1', '2024-01-01', 1, 12, 3, 9, 5),
(1, 'Abteilung2', '2024-02-01', 2, 1, 20, 1, 8),
(2, 'Abteilung1', '2024-01-01', 1, 2, 3, 4, 5),
(2, 'Abeilung2','2024-01-01', 1, 2, 3, 4, 5),
(2, 'Abeilung2','2024-01-01', 1, 2, 3, 4, 5),
(2, 'Abteilung1', '2024-01-01', 1, 2, 3, 4, 5),
(2, 'Abteilung2', '2024-02-01', 2, 1, 2, 1, 1),
(3, 'Abteilung1', '2024-01-01', 1, 2, 3, 4, 5),
(4, 'Abeilung2','2024-01-01', 1, 2, 3, 4, 5),
(5, 'Abeilung2','2024-01-01', 1, 2, 3, 4, 5),
(6, 'Abteilung1', '2024-01-01', 1, 2, 3, 4, 5),
(7, 'Abteilung2', '2024-02-01', 2, 1, 2, 1, 1),
(8, 'Abteilung3', '2024-02-15', 3, 2, 1, 3, 2),
(9, 'Abteilung1', '2024-03-01', 2, 3, 1, 2, 3);

-- Beispiel-Inserts für Quittierung-Tabelle
INSERT INTO digitalisierungAusbildungsnachweis.Quittierung (nachweisId, quittiert, kommentar)
VALUES
(1, 1, 'Quittiert für Abteilung1'),
(2, 0, 'Noch nicht quittiert für Abteilung2'),
(3, 1, 'Quittiert für Abteilung3'),
(4, 0, 'Noch nicht quittiert für Abteilung4'),
(5, 1, 'Quittiert für Abteilung5'),
(6, 0, 'Noch nicht quittiert für Abteilung6'),
(7, 1, 'Quittiert für Abteilung7'),
(8, 0, 'Noch nicht quittiert für Abteilung8'),
(9, 1, 'Quittiert für Abteilung9');
