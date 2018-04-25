package br.com.accenture.projeto.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import br.com.accenture.projeto.model.Cidade;
import br.com.accenture.projeto.model.Estado;

/**
 * Classe reponsável por implementar métodos que manipulam os dados de arquivos
 * JSON
 * 
 * @author Emerson
 *
 */
@Component
public class Localizacao implements Serializable {

	private static final long serialVersionUID = 4309798536454874L;
	
	@Autowired
	Gson gson;
	
	public JsonArray getArrayJson(String caminho) {
		BufferedReader reader = null;
		ClassLoader cl = this.getClass().getClassLoader();
		InputStreamReader isr = new InputStreamReader(cl.getResourceAsStream(caminho));

		reader = new BufferedReader(isr);

		JsonParser parser = new JsonParser();
		return parser.parse(reader).getAsJsonArray();
	}

	/**
	 * Método responsável por ler o arquivo Cidades.json, convertê-lo em uma lista de objetos do tipo Cidade, e em seguida retornará uma lista de cidades de acordo com o codigo do estado passado por parâmetro
	 * @param codigo (Código do estado passado por parâmetro)
	 * @return (Retorna uma lista de cidades de acordo com o codigo do estado passado por parâmetro)
	 */
	public List<Cidade> buscarCidadesPorCodigoDoEstado(String codigo) {

		JsonArray obj = getArrayJson("json/Cidades.json");
		List<Cidade> cidadesDoEstado = new ArrayList<>();
		for (JsonElement jsonElement : obj) {
			Cidade cidade = gson.fromJson(jsonElement, Cidade.class);
			if (cidade.getEstado().equals(codigo)) {
				cidadesDoEstado.add(cidade);
			}
		}
		return cidadesDoEstado;
	}

	/**
	 * Método responsável por ler o arquivo Estados.json, convertê-lo em uma lista
	 * de objetos do tipo Estado, e em seguida retornará uma lista de todos os
	 * estados
	 * 
	 * @return (Retorna uma lista de todos os estados com base no arquivo
	 *         Estados.json)
	 */
	public List<Estado> buscarEstados() {

		JsonArray obj = getArrayJson("json/Estados.json");
		List<Estado> estados = new ArrayList<>();
		for (JsonElement jsonElement : obj) {
			Estado estado = gson.fromJson(jsonElement, Estado.class);
			estados.add(estado);
		}
		return estados;
	}

	/**
	 * Método responsável por ler o arquivo Cidades.json, convertê-lo em uma lista
	 * de objetos do tipo Cidade, e em seguida retornará uma lista de todas as
	 * cidades
	 * 
	 * @return (Retorna uma lista de todas as cidades com base no arquivo
	 *         Cidades.json)
	 */
	public List<Cidade> buscarCidades() {

		JsonArray obj = getArrayJson("json/Cidades.json");
		List<Cidade> cidades = new ArrayList<>();
		for (JsonElement jsonElement : obj) {
			Cidade cidade = gson.fromJson(jsonElement, Cidade.class);
			cidades.add(cidade);
		}
		return cidades;
	}

	/**
	 * Método responsável por ler o arquivo Estados.json, convertê-lo em uma lista
	 * de objetos do tipo Estado, e em seguida retornará o estado de acordo com o id
	 * que foi passado por parâmetro
	 * 
	 * @param idEstado
	 *            (Id do estado que deseja obter do arquivo Estados.json)
	 * @return (Retorna o estado de acordo com o id que foi passado por parâmetro)
	 */
	public Estado buscarEstadoPorCodigo(String idEstado) {

		JsonArray obj = getArrayJson("json/Estados.json");
		for (JsonElement jsonElement : obj) {
			Estado estado = gson.fromJson(jsonElement, Estado.class);
			if (estado.getId().equals(idEstado)) {
				return estado;
			}
		}
		return null;
	}

	/**
	 * Método responsável por ler o arquivo Cidades.json, convertê-lo em uma lista
	 * de objetos do tipo Cidade, e em seguida retornará a cidade de acordo com o id
	 * que foi passado por parâmetro
	 * 
	 * @param idCidade
	 *            (Id da cidade que deseja obter do arquivo Cidades.json)
	 * @return (Retorna a cidade de acordo com o id que foi passado por parâmetro)
	 */
	public Cidade buscarCidadePorCodigo(String idCidade) {

		JsonArray obj = getArrayJson("json/Cidades.json");
		for (JsonElement jsonElement : obj) {
			Cidade cidade = gson.fromJson(jsonElement, Cidade.class);
			if (cidade.getId().equals(idCidade)) {
				return cidade;
			}
		}
		return null;
	}

}