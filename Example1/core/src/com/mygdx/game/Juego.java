package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;


import pantallas.PantallaJuego;

public class Juego extends ApplicationAdapter {

	//Pantalla que divisara el juego (MyGdxGame)
	private Screen pantalla;

	/*Metodo principal al que se llamara para comenzar el juego
	* */
	@Override
	public void create () {

		//Bloqueamos la tecla de vuelta atras de dispositivos Android
		Gdx.input.setCatchBackKey(true);
		//Stablecemos la pantalla con nuestra pantalla de juego
		setPantalla(new PantallaJuego(this));
	}

	/*Metodo que se llamara una vez por frame para renderizar todo lo que sea visible
	* */
	@Override
	public void render () {


		//Limpieza del buffer de la tarjeta grafica
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT |
				GL20.GL_DEPTH_BUFFER_BIT);


		pantalla.render(Gdx.graphics.getDeltaTime());



	}


	/*Metodo usado para cambiar el tamaño de la pantalla
	* */
	@Override
	public void resize(int width, int height) {
		pantalla.resize(width, height);
	}


	/*Metodo que pausa el juego
	* */
	@Override
	public void pause() {
		super.pause();
	}


	/*Metodo que inicia el juego despues de una pausa
	* */
	@Override
	public void resume() {
		super.resume();
	}


	/*Metodo utilizado para liberar la memoria,
	aqui se debe de eliminar (dispose()) todo lo que tenga que ser eliminado
	* */
	@Override
	public void dispose () {

		pantalla.dispose();
	}


	/*Metodo que cambiara la pantalla de juego
	entradas: Screen
	* */
	public void setPantalla(Screen pantalla){

		//Si ya hay una pantalla la borramos
		if (this.pantalla!=null){

			this.pantalla.hide();
			this.pantalla.dispose();
		}
		//Establecemos la nueva pantalla
		this.pantalla =pantalla;

		//Si se establecio la pantalla le damos el tamaño deseado y la mostramos
		if (this.pantalla!=null){

			this.pantalla.resize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			this.pantalla.show();


		}

	}

}
