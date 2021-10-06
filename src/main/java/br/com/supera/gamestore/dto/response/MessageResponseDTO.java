package br.com.supera.gamestore.dto.response;

public class MessageResponseDTO {

	private String message;

	// Gets e sets 
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	//Construtores
	public MessageResponseDTO() {
	}

	public MessageResponseDTO(String message) {
		this.message = message;
	}

	//Método para construir mensagens
	public static MessageResponseDTO builder() {
		return new MessageResponseDTO();
	}

	//Criar a estrutura da mensagem
	public MessageResponseDTO message(String string) {
		MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
		messageResponseDTO.setMessage(string);
		return messageResponseDTO;
	}
	
	//Metodo statico para criar mensagem
	public static MessageResponseDTO createMessageResponse(Long id, String message) {
		return MessageResponseDTO.builder().message(message);
	}
}
