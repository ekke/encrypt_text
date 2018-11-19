#include <QGuiApplication>
#include <QQmlApplicationEngine>

#include "applicationui.hpp"

int main(int argc, char *argv[])
{
    QCoreApplication::setAttribute(Qt::AA_EnableHighDpiScaling);

    QGuiApplication app(argc, argv);
	
	ApplicationUI appui;

    QQmlApplicationEngine engine;
	
    // from QML we have access to ApplicationUI as myApp
    QQmlContext* context = engine.rootContext();
    context->setContextProperty("myApp", &appui);
	
    engine.load(QUrl(QStringLiteral("qrc:/qml/main.qml")));
    if (engine.rootObjects().isEmpty())
        return -1;

    return app.exec();
}
