SmartLibraryPlus – ORM Tabanlı Akıllı Kütüphane Sistemi
Proje Amacı

Bu projenin amacı, Nesneye Yönelik Programlama (OOP) prensiplerini, Object Relational Mapping (ORM) mantığını ve Hibernate framework’ünü kullanarak bir kütüphane otomasyon sisteminin nasıl geliştirilebileceğini uygulamalı olarak göstermektir.

Proje kapsamında, JDBC ile doğrudan SQL yazmadan, nesneler üzerinden veritabanı işlemlerinin nasıl gerçekleştirildiği hedeflenmiştir. Ayrıca katmanlı mimari kullanılarak kodun daha okunabilir, sürdürülebilir ve genişletilebilir olması amaçlanmıştır.

Kullanılan Teknolojiler

Java

Hibernate ORM

SQLite

Maven (kütüphane yönetimi için)

Konsol tabanlı uygulama

Proje Yapısı

Proje katmanlı mimariye uygun şekilde tasarlanmıştır

Entity Yapıları ve İlişkiler

Projede üç temel entity bulunmaktadır:

Book (Kitap)

id

title

author

year

status (AVAILABLE / BORROWED)

Kitapların ödünç durumunu takip edebilmek için status alanı kullanılmıştır.

Student (Öğrenci)

id

name

department

Bir öğrencinin birden fazla ödünç alma kaydı olabileceği için Student – Loan ilişkisi OneToMany olarak tanımlanmıştır.

Loan (Ödünç Alma)

id

borrowDate

returnDate

Student → Loan : ManyToOne

Loan → Book : OneToOne

Bu yapı sayesinde hangi öğrencinin hangi kitabı, hangi tarihte aldığı ve iade edip etmediği takip edilebilmektedir.

Veritabanı Yönetimi

Veritabanı olarak SQLite kullanılmıştır.
Hibernate yapılandırma dosyasında:

hibernate.hbm2ddl.auto = update


özelliği aktif edilerek tabloların Hibernate tarafından otomatik olarak oluşturulması sağlanmıştır.
Uygulama çalıştırıldığında library.db dosyası otomatik olarak oluşmaktadır.

DAO Katmanı ve CRUD İşlemleri

Her entity için ayrı bir DAO sınıfı oluşturulmuştur:

BookDao

StudentDao

LoanDao

Bu sınıflar içerisinde aşağıdaki temel metotlar bulunmaktadır:

save()

update()

delete()

getById()

getAll()

Tüm veritabanı işlemleri Hibernate üzerinden gerçekleştirilmiş, JDBC veya SQL sorguları doğrudan kullanılmamıştır.

Konsol Menü ve İşlevler

Uygulama çalıştırıldığında kullanıcıya konsol üzerinden bir menü sunulmaktadır.
Bu menü aracılığıyla aşağıdaki işlemler yapılabilmektedir:

Kitap ekleme

Kitapları listeleme (durum bilgisi ile birlikte)

Öğrenci ekleme

Öğrencileri listeleme

Kitap ödünç verme

Ödünç listesini görüntüleme

Kitap geri teslim alma

Kitap ödünç verme işlemi sırasında, kitap daha önce ödünç alınmışsa sistem bu işleme izin vermemektedir.
Kitap geri teslim alındığında ise iade tarihi güncellenmekte ve kitabın durumu tekrar AVAILABLE olarak ayarlanmaktadır.

Sonuç

Bu proje ile:

OOP prensipleri

ORM yaklaşımı

Hibernate ile veritabanı yönetimi

Entity ilişkileri

DAO pattern

Konsol tabanlı uygulama geliştirme

konuları uygulamalı olarak öğrenilmiş ve hayata geçirilmiştir.
