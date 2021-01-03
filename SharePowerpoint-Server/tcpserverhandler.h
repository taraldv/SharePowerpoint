#ifndef TCPSERVERHANDLER_H
#define TCPSERVERHANDLER_H

#include <QObject>
#include <QTcpServer>
#include <QTcpSocket>

class TcpServerHandler : public QObject
{
    Q_OBJECT
public:
    TcpServerHandler(QObject *parent = nullptr);
private:
    QTcpServer* mTcpServer;
    QTcpSocket* javaClient = nullptr;
    void acceptTcpConnection();
    void readTcpPacket();
    void parseRequest(QByteArray data);
    void sendKey(QString keyString);
signals:

};

#endif // TCPSERVERHANDLER_H
