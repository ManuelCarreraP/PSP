sequenceDiagram
Cliente->>+Servidor: Solicitud de cálculo<br/>(operandos, operación, opciones)
Servidor->>Servidor: Calculando
activate Servidor
alt Operación válida
Servidor-->>Cliente: Resultado del cálculo
else Operación/entrada no válida
Servidor-->>Cliente: Error (detalle)
end
deactivate Servidor
Servidor-->>Cliente: Resultado
note over Cliente,Servidor:EJEMPLOS
Cliente->>+Servidor: <br/>Solicitud de cálculo<br/>10*9
Servidor->>Servidor: Multiplicando<br/> 10*9 = 900
Servidor-->>-Cliente: Resultado 900
Cliente->>+Servidor: <br/>Solicitud de cálculo<br/>Cos(90)
Servidor->>Servidor: Trigonométrica<br/>Cos(90) = 0
Servidor-->>-Cliente: Resultado<br/> 0
Cliente->>+Servidor: <br/>Solicitud de cálculo<br/>0*0
Servidor->>Servidor: Multiplicando<br/>0*0 = Error
Servidor-->>-Cliente: Resultado<br/> Error
