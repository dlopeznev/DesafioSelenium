# DesafioSelenium

## Introducción

El objetivo de este proyecto es entender por que son utiles las pruebas automatizadas para los sitios web y como se puede hacer para que su mantenimiento sea mas simple a medida de que crece o cambia el mismo.

## Desarrollo

Este proyecto consta de un test unitatio parametrizado que a partir de una lista de nombres de productos es capaz de verificar que cada uno de los mismos es accesible a travez de la web. Esto se logra a partir de una prueba paramatrizable de jUnit y una lista precargada con los productos a verificar.

Adicionalmente se utiliza un WebDriver de Selenium para poder hacer las pruebas en el sitio y el patron PageObject que permite una mayor facilidad en la interaccion con un sitio web, tratando a cada objeto de la pagina como una serie objetos/metodos. Lo cual aumenta la legibilidad y mantenibilidad del código, ya que si en el futuro el formato de algunas partes de la pagina cambiaran, solo seria necesario cambiar el objeto que la abstrae y no los tests que verifican su funcionamiento.

Personalmente fue una experiencia distinta que para el potencial que tiene es suficientemente simple de implementar, la utilizacion de lo aprendido en este desafio hace muy simple las pruebas en un sitio web, mas aun con la parametrizacion del test, ya que permitiria hacer pruebas mucho mas potentes con parametros de entrada y resultados esperados, pero cargados de forma dinamica segun lo que se quiere probar. Por ejemplo, puedo probar un reporte con diferentes filtros (fecha, tipo, categoria, etc) y verificar que los elementos devueltos cumplan con cierta caracteristica común entre ellos, sin necesidad de hacer verificaciones manuales.

## Conclusión

Utilizar test parametrizables junto con el WebDriver para Selenium es una combinacion potente la cual puede automatizar muchos de los test en paginas web que generalmente deben hacerse de forma manual. Si bien su implementacion en un proyecto de tamaño medio a grande puede llevar un tiempo considerable, las mejoras a largo plazo hacen que ese tiempo se pague por si solo.
