/****************************************************************************
** Meta object code from reading C++ file 'rectangledialog.h'
**
** Created by: The Qt Meta Object Compiler version 67 (Qt 5.9.1)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../RectangleDialog/rectangledialog.h"
#include <QtCore/qbytearray.h>
#include <QtCore/qmetatype.h>
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'rectangledialog.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 67
#error "This file was generated using the moc from 5.9.1. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
QT_WARNING_PUSH
QT_WARNING_DISABLE_DEPRECATED
struct qt_meta_stringdata_RectangleDialog_t {
    QByteArrayData data[11];
    char stringdata0[173];
};
#define QT_MOC_LITERAL(idx, ofs, len) \
    Q_STATIC_BYTE_ARRAY_DATA_HEADER_INITIALIZER_WITH_OFFSET(len, \
    qptrdiff(offsetof(qt_meta_stringdata_RectangleDialog_t, stringdata0) + ofs \
        - idx * sizeof(QByteArrayData)) \
    )
static const qt_meta_stringdata_RectangleDialog_t qt_meta_stringdata_RectangleDialog = {
    {
QT_MOC_LITERAL(0, 0, 15), // "RectangleDialog"
QT_MOC_LITERAL(1, 16, 20), // "rectangleLengthWidth"
QT_MOC_LITERAL(2, 37, 0), // ""
QT_MOC_LITERAL(3, 38, 12), // "lengthString"
QT_MOC_LITERAL(4, 51, 11), // "widthString"
QT_MOC_LITERAL(5, 63, 25), // "on_lengthEdit_textChanged"
QT_MOC_LITERAL(6, 89, 24), // "on_widthEdit_textChanged"
QT_MOC_LITERAL(7, 114, 16), // "calculateClicked"
QT_MOC_LITERAL(8, 131, 22), // "rectangleAreaCalculate"
QT_MOC_LITERAL(9, 154, 9), // "lengthStr"
QT_MOC_LITERAL(10, 164, 8) // "widthStr"

    },
    "RectangleDialog\0rectangleLengthWidth\0"
    "\0lengthString\0widthString\0"
    "on_lengthEdit_textChanged\0"
    "on_widthEdit_textChanged\0calculateClicked\0"
    "rectangleAreaCalculate\0lengthStr\0"
    "widthStr"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_RectangleDialog[] = {

 // content:
       7,       // revision
       0,       // classname
       0,    0, // classinfo
       5,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       1,       // signalCount

 // signals: name, argc, parameters, tag, flags
       1,    2,   39,    2, 0x06 /* Public */,

 // slots: name, argc, parameters, tag, flags
       5,    1,   44,    2, 0x08 /* Private */,
       6,    1,   47,    2, 0x08 /* Private */,
       7,    0,   50,    2, 0x08 /* Private */,
       8,    2,   51,    2, 0x08 /* Private */,

 // signals: parameters
    QMetaType::Void, QMetaType::QString, QMetaType::QString,    3,    4,

 // slots: parameters
    QMetaType::Void, QMetaType::QString,    2,
    QMetaType::Void, QMetaType::QString,    2,
    QMetaType::Void,
    QMetaType::Void, QMetaType::QString, QMetaType::QString,    9,   10,

       0        // eod
};

void RectangleDialog::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        RectangleDialog *_t = static_cast<RectangleDialog *>(_o);
        Q_UNUSED(_t)
        switch (_id) {
        case 0: _t->rectangleLengthWidth((*reinterpret_cast< const QString(*)>(_a[1])),(*reinterpret_cast< const QString(*)>(_a[2]))); break;
        case 1: _t->on_lengthEdit_textChanged((*reinterpret_cast< const QString(*)>(_a[1]))); break;
        case 2: _t->on_widthEdit_textChanged((*reinterpret_cast< const QString(*)>(_a[1]))); break;
        case 3: _t->calculateClicked(); break;
        case 4: _t->rectangleAreaCalculate((*reinterpret_cast< const QString(*)>(_a[1])),(*reinterpret_cast< const QString(*)>(_a[2]))); break;
        default: ;
        }
    } else if (_c == QMetaObject::IndexOfMethod) {
        int *result = reinterpret_cast<int *>(_a[0]);
        void **func = reinterpret_cast<void **>(_a[1]);
        {
            typedef void (RectangleDialog::*_t)(const QString & , const QString );
            if (*reinterpret_cast<_t *>(func) == static_cast<_t>(&RectangleDialog::rectangleLengthWidth)) {
                *result = 0;
                return;
            }
        }
    }
}

const QMetaObject RectangleDialog::staticMetaObject = {
    { &QDialog::staticMetaObject, qt_meta_stringdata_RectangleDialog.data,
      qt_meta_data_RectangleDialog,  qt_static_metacall, nullptr, nullptr}
};


const QMetaObject *RectangleDialog::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *RectangleDialog::qt_metacast(const char *_clname)
{
    if (!_clname) return nullptr;
    if (!strcmp(_clname, qt_meta_stringdata_RectangleDialog.stringdata0))
        return static_cast<void*>(const_cast< RectangleDialog*>(this));
    if (!strcmp(_clname, "Ui::RectangleDialog"))
        return static_cast< Ui::RectangleDialog*>(const_cast< RectangleDialog*>(this));
    return QDialog::qt_metacast(_clname);
}

int RectangleDialog::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QDialog::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 5)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 5;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 5)
            *reinterpret_cast<int*>(_a[0]) = -1;
        _id -= 5;
    }
    return _id;
}

// SIGNAL 0
void RectangleDialog::rectangleLengthWidth(const QString & _t1, const QString _t2)
{
    void *_a[] = { nullptr, const_cast<void*>(reinterpret_cast<const void*>(&_t1)), const_cast<void*>(reinterpret_cast<const void*>(&_t2)) };
    QMetaObject::activate(this, &staticMetaObject, 0, _a);
}
QT_WARNING_POP
QT_END_MOC_NAMESPACE
