#include "tcpserverhandler.h"

TcpServerHandler::TcpServerHandler(QObject *parent) : QObject(parent)
{
    mTcpServer = new QTcpServer();
    connect(mTcpServer, &QTcpServer::newConnection, this, &TcpServerHandler::acceptTcpConnection);
    int port = 3322;
    mTcpServer->listen(QHostAddress::Any, port);
    //qDebug() << "TCP Listening on port:" << port;
}

void TcpServerHandler::acceptTcpConnection(){
    QTcpSocket* socket = mTcpServer->nextPendingConnection();
    if (!socket){
        //qDebug() << "Error: got invalid pending connection!";
    }

    connect(socket, &QIODevice::readyRead, this, &TcpServerHandler::readTcpPacket);
    //connect(socket, &QAbstractSocket::disconnected, this, &TcpServerHandler::lostConnection);
}

void TcpServerHandler::readTcpPacket(){

    QTcpSocket* readSocket = qobject_cast<QTcpSocket*>(sender());
    /*QHostAddress senderAddress = readSocket->peerAddress();
    int port = readSocket->peerPort();*/

    QByteArray data = readSocket->readAll();
    int messageCode = data[0];
    if(messageCode == 0){
        javaClient = readSocket;
        //Am unable to check if javaClient == nullptr
       // qDebug() << "Assigned javaclient";
    } else {
        parseRequest(data);
        readSocket->close();
    }
}

void TcpServerHandler::parseRequest(QByteArray data){
    int newLine = 10;
    int carrReturn = 13;
    QString line = "";
    for(int i=0;i<data.size();i++){
        if(data.at(i) == carrReturn && data.at(i+1) == newLine){
            //Skips this char and the next;
            i++;
            if(line.contains("btnSubmit")){
                sendKey(line);
                return;
            }
            line = "";
            continue;
        }
        line.append(data.at(i));
    }
    if(line.contains("btnSubmit")){
        sendKey(line);
    }
}

void TcpServerHandler::sendKey(QString keyString){
    int equalsIndex = keyString.indexOf("=");
    //qDebug() << equalsIndex;
    QString key = keyString.right(keyString.length()-equalsIndex-1);
    QByteArray output;
    output.append(key.length());
    output.append(key);
    if(javaClient){
        javaClient->write(output);
    }
}


