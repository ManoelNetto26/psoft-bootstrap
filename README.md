
# Atividade 5 — Sistema de entregas

Sistema simples de gerenciamento logístico de pedidos com modalidades de entrega substituíveis.

## Modelo

- `Pedido` mantém cliente, endereços, peso, distância, modalidade e estado da entrega.
- `ModalidadeEntrega` define o cálculo de custo e prazo; novas modalidades podem ser adicionadas sem mudar o controlador ou as modalidades existentes.
- `EntregaConvencional` calcula tarifa-base mais tarifa por quilômetro.
- `EntregaExpressa` aplica adicional de preço e redução do prazo convencional.
- `EntregaEconomica` aplica desconto e acrescenta dias ao prazo convencional.
- `ControladorLogistico` centraliza o registro e o acompanhamento dos pedidos. A modalidade pode ser alterada enquanto o pedido estiver registrado, mas não depois do despacho.

Os valores de tarifa, desconto e prazo são parâmetros dos construtores, pois a especificação da atividade não fixa valores numéricos. O peso é armazenado no pedido e pode ser usado por outras modalidades; não altera as fórmulas atuais porque a especificação descreve o custo convencional em função da distância.

## Compilar, executar e testar

```sh
mkdir -p /tmp/psoft-atv5-classes
javac -d /tmp/psoft-atv5-classes src/main/java/*.java src/test/java/*.java
java -cp /tmp/psoft-atv5-classes Bootstrap
java -ea -cp /tmp/psoft-atv5-classes Atividade5Test
```

O exemplo em `Bootstrap` usa valores ilustrativos e demonstra as três modalidades e o despacho de um pedido.
