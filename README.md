<center><img src = "https://github.com/ViniciusKendy17/Manga_hub/assets/135672206/f7a2205e-4d98-4a1d-88e1-1a00e38a07ac"   ></center>

<h1>> Status: Developing ⚠️</h1>
<br>
<h1>About the Manga_Hub</h1>
<p>Manga_Hub is a web commerce where you can make a registry and sell the products you want, such as a manga, an anime action figure, manhwa and more.
Our gool here is to provide a easier way to sell what you intend to, we want to make the slow process of buying and selling products be easy and a way faster</p>

<h2>Technologies:</h2>

<div  style="display: inline_block"><br>
  <img align="center"  height="100" width="130" src="https://github.com/ViniciusKendy17/Manga_hub/assets/135672206/011d00e6-1cb3-4fd1-8004-d43756cbadc7">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img align="center"  height="100" width="130" src="https://github.com/ViniciusKendy17/Manga_hub/assets/135672206/bb34da66-2bae-4316-89dc-5b6f5819b666">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img align="center"  height="100" width="100" src="https://github.com/ViniciusKendy17/Manga_hub/assets/135672206/1fa9f2c1-2640-454b-a734-fdaee4ed6d2a">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img align="center"  height="100" width="100" src="https://github.com/ViniciusKendy17/Manga_hub/assets/135672206/7b3c4451-174b-4fe6-81e9-f9607a06d573">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img align="center"  height="100" width="100" src="https://github.com/ViniciusKendy17/Manga_hub/assets/135672206/5d9fa991-9b54-4ca1-8f07-9252ebf0b9ea">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img align="center"  height="100" width="100" src="https://github.com/ViniciusKendy17/Manga_hub/assets/135672206/0654d61d-e07b-4ce7-8191-97ca73905fcb">
</div>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


<h2>Main features</h2>

+ Search for products by name;
+ Add a product into the shop cart;
+ open a product card and see all of its details;
+ sign up or sign in the system;
+ Register a product


<h2>Getting Started</h2>

To get started with Manga Hub, you will need to:

1. Clone the repository from GitHub:

```
git clone https://github.com/manga-hub/manga-hub.git
```

2. Install the dependencies:

```
mvn install
```

3. Run the application:

```
mvn spring-boot:run
```

<h2>Project Structure</h2>

The Manga Hub project is structured as follows:

* `src/main/java`: Contains the Java source code for the application.
* `src/main/resources`: Contains the configuration files for the application.
* `src/test/java`: Contains the unit tests for the application.

<h2>Main Application Class</h2>

The main application class is `MangaHubApplication`. This class is responsible for bootstrapping the Spring Boot application.

```java
@SpringBootApplication
public class MangaHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangaHubApplication.class, args);
	}

}
```

The `@SpringBootApplication` annotation tells Spring Boot that this class is the main application class. It also enables auto-configuration, which means that Spring Boot will automatically configure the application based on the dependencies that are present in the classpath.

<h2>Running the Application</h2>

To run the Manga Hub application, you can use the following command:

```
mvn spring-boot:run
```

This command will compile the application, start the Spring Boot application server, and deploy the application to the server.





