var BomBar = BomBar || {}

/**
 * @name função construtora para utilizar algumas funções js no formulário de cadastroBar.html
 *
 * @description
 * Nesta funcao sao aplicadas algumas regras para preenchimento do formulário de bar
 *
 * @author Emerson Davi
 **/
BomBar.CadastroOuEdicaoBar = (function() {

	/**
	 * Referências aos componentes da página
	 */
	function CadastroOuEdicaoBar() {
		this.btnDelete = $('#deletebar');
		this.formDelete = $('#form-delete');
		this.urlCidades = '/bares/listacidades';
		this.listaCidade = [];
		this.selectEstado = $('#estado');
		this.selectCidade = $('#cidade');
	};

	/**
	 * Função que é inicializada assim que a página que está utilizando este arquivo js, é acessada, onde nela contém uma função que é acionada conforme hà mudanças no select de estados, e outra que é acionada no momento em que o botão remover é clicado
	 */
	CadastroOuEdicaoBar.prototype.iniciar = function() {
		this.selectEstado.on('change', getCidadesPorId.bind(this));
		this.btnDelete.on('click', openConfirmaRemocao.bind(this));
	}

	/**
	 * Função responsável por abrir o modal de confirmação de remoção
	 */
	function openConfirmaRemocao() {
		var mainContext = this;
		swal({
			title: "Tem certeza?",
			text: "Você não poderá recuperar o item após a exclusão.",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "Sim, exclua agora!",
			confirmButtonClass: "js-confirm",
			closeOnConfirm: false,
			showLoaderOnConfirm: true,
			buttonsStyling: false
		},
		function(isConfirm) {
			if (isConfirm) {
				mainContext.formDelete.submit();
				swal("Removido!", "O item foi removido com sucesso.", "success");
			} else {
				swal("Cancelado", "A remoção do item foi cancelada.", "error");
			}
		});
	};

	/**
	 * Função responsável por realizar uma requisição GET via AJAX para obter uma lista de cidades em formato JSON, passando por parâmetro na requisição um id referente ao estado que foi selecionado no select da página
	 */
	function getCidadesPorId() {
		if (this.selectEstado.val().trim()) {
			$.ajax({
				dataType: 'json',
				url: this.urlCidades + "/" + this.selectEstado.val(),
				method: 'GET',
				success: sucessCidade.bind(this),
			});
		}
	};

	/**
	 * Função que é responsável por popular o select de cidades na página cadastroBar.html conforme a lista de cidades em formato JSON que recebe por parâmetro
	 */
	function sucessCidade(data) {

		this.selectCidade.find('option').remove().end().append('<option value="">Selecione</option>');

		this.listaCidade = data;

		for (var i = 0; i < this.listaCidade.length; i++) {
			this.selectCidade.append('<option value="' + this.listaCidade[i].id + '">' + this.listaCidade[i].nome + '</option>');
		}

	};

	return CadastroOuEdicaoBar;

})();

/**
 * @name Funcao para executar funcao construtora
 *
 *
 * @author Emerson Davi
 **/
$(function() {

	cadastroOuEdicaoBar = new BomBar.CadastroOuEdicaoBar();
	cadastroOuEdicaoBar.iniciar();

});
