## SharePowerPoint
Software to allow multiple clients send key actions to a single host, with the intent of letting them control the PowerPoint presentation<br>
Consists of several parts:
<ul>
  <li> PHP webpage as entry point for clients
  <li> C++ software running on a server listening for requests sent by PHP
  <li> Java software running on the host, communicating with the C++ software and sending keystrokes to the OS
  </ul>

![](https://tarves.no/gif/powerpointshare.PNG)
<br>The server running the C++ can also be the host for the webpage
