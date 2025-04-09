package org.example.prensentation.pres;

import org.example.prensentation.dao.IDao;
import org.example.prensentation.metier.IMetier;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Pres2 {
    public static void main(String[] args) {
        File file = new File("config.txt");

        // Vérification de l'existence du fichier
        if (!file.exists()) {
            System.out.println("Le fichier config.txt est introuvable.");
            return;
        }

        try {
            // Utilisation correcte du Scanner
            Scanner scanner = new Scanner(file);

            // Lecture des noms des classes
            String daoClassName = scanner.nextLine();
            Class<?> cDao = Class.forName(daoClassName);
            IDao dao = (IDao) cDao.getDeclaredConstructor().newInstance();

            String metierClassName = scanner.nextLine();
            Class<?> cMetier = Class.forName(metierClassName);
            IMetier metier = (IMetier) cMetier.getConstructor(IDao.class).newInstance(dao);

            // Affichage du résultat
            System.out.println("RES = " + metier.calcul());

        } catch (FileNotFoundException e) {
            System.out.println("Le fichier config.txt est introuvable.");
        } catch (ClassNotFoundException e) {
            System.out.println("Une des classes spécifiées n'a pas été trouvée.");
        } catch (Exception e) {
            System.out.println("Une erreur est survenue : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
