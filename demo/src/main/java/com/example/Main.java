package com.example;

import com.example.entities.Machine;
import com.example.entities.Salle;
import com.example.services.MachineService;
import com.example.services.SalleService;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        SalleService salleService = new SalleService();
        MachineService machineService = new MachineService();

        System.out.println("=== Début du programme ===");

        // --- Création et insertion de salles ---
        Salle salle1 = new Salle("A1");
        Salle salle2 = new Salle("B2");
        salleService.create(salle1);
        salleService.create(salle2);

        System.out.println("✅ Salles créées : " + salle1.getCode() + ", " + salle2.getCode());

        // --- Création et insertion de machines ---
        Machine machine1 = new Machine("M123", new Date());
        machine1.setSalle(salleService.findById(salle1.getId()));

        Machine machine2 = new Machine("M124", new Date());
        machine2.setSalle(salleService.findById(salle2.getId()));

        machineService.create(machine1);
        machineService.create(machine2);

        System.out.println("✅ Machines créées : " + machine1.getRef() + ", " + machine2.getRef());

        // --- Affichage des salles et leurs machines ---
        System.out.println("\n=== Liste des salles et leurs machines ===");
        for (Salle salle : salleService.findAll()) {
            System.out.println("Salle: " + salle.getCode());
            if (salle.getMachines() != null && !salle.getMachines().isEmpty()) {
                for (Machine machine : salle.getMachines()) {
                    System.out.println("  Machine: " + machine.getRef());
                }
            } else {
                System.out.println("  (Aucune machine)");
            }
        }

        // --- Utilisation de la méthode findBetweenDate ---
        Date d1 = new Date(110, 0, 1); // 1er janvier 2010 (deprecated mais utile pour test)
        Date d2 = new Date(); // Date actuelle

        System.out.println("\n=== Machines achetées entre " + d1 + " et " + d2 + " ===");
        List<Machine> machines = machineService.findBetweenDate(d1, d2);
        for (Machine m : machines) {
            System.out.println("Machine: " + m.getRef() + " achetée le " + m.getDateAchat());
        }

        // --- Test update machine ---
        machine1.setRef("M999");
        boolean updated = machineService.update(machine1);
        System.out.println(updated ? "\n✅ Machine mise à jour: " + machine1.getRef()
                : "\n❌ Erreur de mise à jour");

        // --- Test delete machine ---
        boolean deleted = machineService.delete(machine2);
        System.out.println(deleted ? "✅ Machine supprimée: " + machine2.getRef()
                : "❌ Erreur de suppression machine");

        // --- Test update salle ---
        salle1.setCode("C3");
        boolean salleUpdated = salleService.update(salle1);
        System.out.println(salleUpdated ? "✅ Salle mise à jour: " + salle1.getCode()
                : "❌ Erreur de mise à jour salle");

        // --- Test delete salle ---
        boolean salleDeleted = salleService.delete(salle2);
        System.out.println(salleDeleted ? "✅ Salle supprimée: " + salle2.getCode()
                : "❌ Erreur de suppression salle");

        // --- Liste finale ---
        System.out.println("\n=== Liste finale des salles ===");
        for (Salle salle : salleService.findAll()) {
            System.out.println("Salle: " + salle.getCode());
        }

        System.out.println("\n=== Fin du programme ===");
    }
}