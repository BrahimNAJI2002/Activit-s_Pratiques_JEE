# Partie 1 : Application du support et vidéo


## 1.	Couche DAO
On crée l’interface IDao
```java
package org.example.ma.dao;

public interface IDao {
    double getData();
}

```

Puis une implémentation dans IDaoImpl
```java
package org.example.ma.dao;

public class DaoImpl implements IDao {
    @Override
    public double getData() {
        /*
		Se connecter à la base de BD pour récupérer la température
		*/
        System.out.println("version base de données");
        double temp = Math.random()*40;
        return temp;
    }
}

```

## 2.	Couche métier
On crée l’interface IMetier
```java
package org.example.ma.metier;

public interface IMetier {
    double calcul();
}

```

Puis une implémentation dans IMetierImpl en utilisant un couplage faible
```java
package org.example.ma.metier;

import org.example.ma.dao.IDao;

public class MetierImpl implements IMetier {
    //Couplage faible
    private IDao dao;
    @Override
    public double calcul() {
        double tmp = dao.getData();
        double res = tmp * 3 / Math.cos(tmp * Math.PI);
        return res;
    }

    /*
	Injecter dans la variable dao un objet d'une class qui implemente l'interface IDao
	*/
    public void setDao(IDao dao){
        this.dao = dao;
    }
}

```
## 3.	Injection des dépendances
Faire l'injection des dépendances :
### a: Par instanciation statique
Dans la classe "Presentation", les dépendances sont statiquement injectées en créant des instances de "DaoImpl2" et de "MetierImpl". Ensuite, la méthode "setDao()" est appelée pour définir l'instance "dao" dans "MetierImpl".
```java
package org.example.ma.presentation;


import org.example.ma.ext.DaoImpl2;
import org.example.ma.metier.MetierImpl;

public class Presentation {
    public static void main(String[] args) {
        /*
        Injection des dependances par instantiation statique
        => new
         */
        DaoImpl2 dao=new DaoImpl2();
        MetierImpl metier=new MetierImpl();
        metier.setDao(dao);
        System.out.println("Resultat=" + metier.calcul());
    }
}

```
### b: Par instanciation  dynamique

Toujours dans le contexte de « code ouvert à l’extension et fermé à la modification » , l’instanciation statique nous oblige à modifier le code au cas où on crée une nouvelle extension.
Instanciation dynamique par contre injecte automatiquement les dépendances sans changer de codes. Il suffit de modifier le fichier config.txt.

```java
package org.example.ma.presentation;


import org.example.ma.dao.IDao;
import org.example.ma.metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Presentation2 {
    public static void main(String[] args) throws Exception {
        Scanner scanner=new Scanner(new File("config.txt"));

        String daoClassName=scanner.nextLine();
        Class cDao=Class.forName(daoClassName);
        IDao dao=(IDao) cDao.newInstance();

        String metierClassName=scanner.nextLine();
        Class cMetier=Class.forName(metierClassName);
        IMetier metier=(IMetier) cMetier.newInstance();

        Method method=cMetier.getMethod("setDao", IDao.class);
        method.invoke(metier, dao);// dynamic
        // metier.setDao(dao); static

        System.out.println("Résultat " + metier.calcul());
    }
}

```

Le fichier config.txt contient les noms et chemin des classes utilisées.

```txt
org.example.ma.ext.DaoImplVWS
org.example.ma.metier.MetierImpl
```

## 4.	Framework Spring
Pour cette partie on exploite le framework Spring pour injecter les dépendances automatiquement :

### Par XML
```java
package org.example.ma.presentation;

import org.example.ma.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PresSpringXml {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext( "applicationContext.xml");
        IMetier metier=(IMetier) context.getBean( "metier");
        System.out.println("Result : " + metier.calcul());

    }
}

```

Voici la structure du fichier applicationContext.XML

```XML
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="dao" class="org.example.ma.ext.DaoImpl2"></bean>
    <bean id="metier" class="org.example.ma.metier.MetierImpl">
        <property name="dao" ref="dao"></property>
    </bean>
</beans>
```

### Par annotations

```java
package org.example.ma.presentation;

import org.example.ma.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PreSpringAnnotations {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("ma.enset.dao","ma.enset.metier");
        IMetier metier =context.getBean(IMetier.class);
        System.out.println(metier.calcul());
    }
}

```