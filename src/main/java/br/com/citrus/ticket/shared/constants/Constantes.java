package br.com.citrus.ticket.shared.constants;

public class Constantes {

	public static final String CITRUS_LANG = "CITRUS_LANG";
	public static final String OBJ = "obj";
	public static final String TMP = "/tmp/";
	public static final String PATH_SISTEMA = "/opt/crm";
	public static final String CONTEXT_PATH = "contextPath";
	public static final String PAGINATOR = "paginator";
	public static final String CAMPOS_UNICOS = "camposUnicos";
	public static final String JAVAX_VALIDATION_CONSTRAINTS_NOT_NULL_MESSAGE = "javax.validation.constraints.NotNull.message";
	public static final String VALIDATION_MESSAGES = "ValidationMessages";
	public static final String RELATORIO_ = "relatorio.";
	public static final String NOME_RELATORIO = "relatorio";
	
	//objetos
	public static final String OBJETO_CONFIGURACOES_GERAIS = "objeto.configuracoes.gerais";
	public static final String OBJETO_USUARIO = "objeto.usuario";
	
	// separador dos tipos de chamada do filtro.
	public static final String DD_MM_YYYY = "dd/MM/yyyy";
	public static final String MM_DD_YYYY = "MM/dd/yyyy";
	public static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
	public static final String DD_MM_YYYY_HH_MM = "dd/MM/yyyy HH:mm";
	public static final String DD_MM_YYYY_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	
	// formatando a data no banco
	public static final String DD_MM_YYYY_HH_MM_SS_BANCO_DADOS = "dd/MM/yyyy HH24:MI:ss";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DD_MM_YYYY_ = "dd-MM-yyyy";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-ddTHH:mm:ss";
	public static final String YYYY_MM_DD_T2_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MI_BD = "yyyy-MM-dd HH24:MI";
	public static final String HH_MM = "HH:mm";
	public static final String HH_MM_SS = "HH:mm:ss";
	public static final String DD_MM_YYYY_HH_MM_SS_UNDERSCORE_SEPARATOR = "dd_MM_yyyy_HH_mm_ss_SSS";
	
	// servidor ldap
	public static final String PORTA_PADRAO = "389";
	public static final String NOME_PADRAO_SERVIDOR_UNITY = "Unity LDAP - ";
	
	public static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";
	
	// anexos
	public static final String CLIENTES = "clientes";
	public static final String TIPOS_OCORRENCIAS = "tiposOcorrencias";
	public static final String OCORRENCIAS = "ocorrencias";
	public static final String CONFIGURACOES_GERAIS = "configuracoesGerais";
	public static final String SOLUCAO = "solucao";
	public static final String USUARIOS = "usuarios";
	public static final String FORMULARIOS = "formularios";
	public static final String ATENDIMENTOS = "atendimentos";
	public static final String AGENDAMENTO = "agendamento";
	public static final String ATENDIMENTOS_AGENDA = "atendimentosAgenda";
	public static final String ATIVIDADES = "atividades";
	public static final String TIPO_PROPOSTA = "tipoProposta";
	public static final String PROPOSTA = "proposta";
	public static final String ANEXOS = "anexos";
	public static final String ITEM_PROPOSTA_ANEXO = "itemPropostaAnexo";
	public static final String COMENTARIOS = "comentarios";
	public static final String CHAT = "chat";
	public static final String OPORTUNIDADES = "oportunidades";
	public static final String LEADS = "leads";
	public static final String CONTATOS = "contatos";
	public static final String FLUXO = "fluxos";
	public static final String ETAPA = "etapas";
	public static final String PROCESSOS = "processos";
	
	//separador de campos do filtro
	public static final String SEPARADOR_PONTO_VIRGULA = ";";
	public static final String SEPARADOR_VIRGULA = ",";
	
	//monitoramento
	public static final String GRUPO_MONITORAMENTO_SELECIONADO = "grupoMonitoramentoSelecionadoId";
	
	//cliente tamanho codigo
	public static final int  QTD_CARACTERES_CODIGO_CLIENTE = 6;
	
	public static final String STRATUS_SESSION = "STRATUS_SESSION";
	public static final String SISTEMA_NOME_RELATORIO = "sistema_nome_relatorio";
	public static final String MASCARA_MONETARIA = "###,###,##0.00";
	
	public static final int TAMANHO_CODIGO = 9;
	
	public static final boolean USAR_NOTIFICATION_SERVER = true;

	public static final String CHAVE_SISTEMA = "citruscx";
	
	// o prefixo "variable" está presente apenas nos eventos do callcenter
	public static final String VARIABLE_UNITY = "variable_UNITY_";
	public static final String WEBRTC_CALL_ID = "webRtcCallId";
	
	// com a migração dos eventos para o notification server, o prefixo "variable" foi removido
//	public static final String VARIABLE_UNITY = "UNITY_";
	
	public static final String VARIABLE_VERTO = "variable_verto_h_UNITY_";
	
	public static final String API_PROPOSTA_BASE_URL = "/api/proposta/";

	public static final long INTERVALO_CONSULTA_LICENCA_SEGUNDOS = 86400;

	public static final String IS_USUARIO_SISTEMA = "isUsuarioSistema";
	
	public static final String ATENDIMENTO_CHAT_TIPO_ATENDIMENTO = "atnData_attendance_type";
	
	public static final String ATENDIMENTO_CHAT_ID_ATENDIMENTO = "atnData_attendance_id";
	
	public static final String PREFIX_ATN_DATA = "atnData_";
	public static final String PREFIX_ATN_CUSTOM_FIELD = "atnCustomField_";

	public static final String MSG_ALERT = "msgAlert";

	public static final String WEBDOCTOR_SYSTEM_BUILD = "webdoctor";
	public static final String CITRUSCX_SYSTEM_BUILD = "citruscx";
	
	public static final String ACCEPT = "Accept";
	public static final String AUTHORIZATION = "Authorization";
	public static final String APPLICATION_JSON = "application/json";
	public static final String CONTENT_TYPE = "Content-type";
}
