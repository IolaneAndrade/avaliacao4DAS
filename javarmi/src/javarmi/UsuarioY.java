package javarmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class UsuarioY implements ServicoListener {
	
	/*
	 * Classe stub
	 * 
	 * Realiza conexão com o servidor, lê e retorna dados a partir do método calculoEfetuado
	 * Escreve e transmite os parâmetros para a máquina virtual a partir do método addListener
	 */
	
	public static void main(String[] args) {
		
		try {
			
			Cliente cliente = new Cliente();
			cliente.setValor(10);
			
			String nomeServico = "MeuServico";
			int porta = 12345;

			ServicoListener clienteY = new UsuarioY();
			ServicoListener clienteBdistribuido = (ServicoListener) UnicastRemoteObject.exportObject(clienteY, 0);

			Registry registry = LocateRegistry.getRegistry(porta);
			Servico servicoRemoto = (Servico) registry.lookup(nomeServico);
			servicoRemoto.addListener(clienteBdistribuido);

			System.out.println("Cliente Y enviando: " + cliente.getValor());
			servicoRemoto.setY(cliente.getValor());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void calculoEfetuado(double resultado) throws RemoteException {
		
		System.out.println("Servidor devolveu para Cliente Y o resultado: " + resultado);
	}
}