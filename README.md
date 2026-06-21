# ArizaTakip

ArizaTakip, kurum içi IT/teknik destek ekiplerinin gelen **bilgisayar/sistem arıza bildirimlerini** takip edip yönetebilmesi için geliştirilmiş bir **Android uygulamasıdır**. Arızalar; PC adı, IP adresi, kullanıcı adı, sorun açıklaması ve tarih bilgisiyle birlikte listelenir; durumları tek dokunuşla güncellenebilir ve yeni arızalar için anlık bildirim (push notification) desteği bulunur.

## Özellikler

- 📋 **Arıza Listeleme:** Arızalar, navigation drawer üzerinden iki sekmede gösterilir:
  - **"İşlem ve sıradakiler"** → durumu `ISLEME ALINDI`, boş veya `NULL` olan arızalar (işlemde olanlar önce listelenir)
  - **"Tamamlananlar"** → durumu `TAMAMLANDI` veya `REDDEDİLDİ` olan arızalar
- 🃏 **Kart Tasarımı:** Her arıza; PC adı, IP adresi, kullanıcı adı, sorun açıklaması, tarih ve renk kodlu durum göstergesiyle bir kart üzerinde listelenir.
- 🔄 **Durum Güncelleme:** Her kartın "..." menüsünden arıza durumu tek tıkla değiştirilebilir:
  - **İşleme Alındı**
  - **Tamamlandı**
  - **Sıraya Al** (durumu boşa çevirir, kuyruğa geri alır)
- 🔔 **Anlık Bildirimler:** Firebase Cloud Messaging (FCM) entegrasyonu sayesinde yeni arıza bildirimleri push notification olarak iletilebilir.
- 🗄️ **SQL Server Bağlantısı:** Uygulama doğrudan JTDS sürücüsü ile bir Microsoft SQL Server veritabanına bağlanarak verileri okur/günceller.

## Mimari ve Veri Akışı

Uygulama, `ArizaBildirimTakip` adlı bir SQL Server tablosu üzerinden çalışır:

| Sütun (sıra) | Açıklama |
|---|---|
| 1 | Arıza ID |
| 2 | PC Adı |
| 3 | IP Adresi |
| 4 | Kullanıcı Adı |
| 5 | Sorun Açıklaması |
| 6 | Tarih |
| 7 | Durum (`ISLEME ALINDI`, `TAMAMLANDI`, `REDDEDİLDİ`, boş/NULL) |

**Akış:**
1. `ConnectionHelper` → JTDS sürücüsüyle SQL Server'a bağlanır.
2. `sql_Veri.VeriOlustur(durum)` → İlgili duruma göre arıza kayıtlarını çekip `Arizalar` nesnelerine dönüştürür.
3. `ArizaAdapter` → Kayıtları `RecyclerView` üzerinde kart tasarımıyla listeler.
4. `sqlUpdate.VeriGuncelle(id, yeniDurum)` → Seçilen arızanın durumunu veritabanında günceller.
5. `MyFirebaseMessagingService` / `HttpHelper` → Yeni arıza geldiğinde FCM üzerinden bildirim gönderir.

## Kullanılan Teknolojiler

- **Dil:** Java
- **Platform:** Android (minSdk 24, targetSdk/compileSdk 33)
- **UI:** Navigation Component (Drawer + Fragment), RecyclerView, ViewBinding
- **Veritabanı Bağlantısı:** [jTDS JDBC Driver](https://jtds.sourceforge.net/) ile Microsoft SQL Server
- **Bildirimler:** Firebase Cloud Messaging (FCM)
- **Build Sistemi:** Gradle

## Proje Yapısı

```
ArizaTakip/
├── app/
│   ├── build.gradle
│   ├── google-services.json        # Firebase yapılandırması
│   ├── libs/
│   │   └── jtds-1.2.7.jar          # SQL Server JDBC sürücüsü
│   └── src/main/java/com/example/arizatakip_v1/
│       ├── MainActivity.java        # Ana ekran, drawer + nav host
│       ├── ConnectionHelper.java    # SQL Server bağlantı bilgileri
│       ├── sql_Veri.java            # Arıza kayıtlarını sorgulama
│       ├── sqlUpdate.java           # Arıza durumu güncelleme
│       ├── Arizalar.java            # Arıza veri modeli
│       ├── ArizaAdapter.java        # RecyclerView adapter + kart menüsü
│       ├── islemBosFragment.java    # "İşlem ve sıradakiler" sekmesi
│       ├── redTamamFragment.java    # "Tamamlananlar" sekmesi
│       ├── MyFirebaseMessagingService.java  # FCM bildirim alma
│       └── HttpHelper.java          # FCM bildirim gönderme
└── LICENSE
```

## Kurulum ve Çalıştırma

### Gereksinimler
- Android Studio (Giraffe veya üzeri önerilir)
- JDK 8+
- Microsoft SQL Server (erişilebilir bir IP/port üzerinden)
- Bir Firebase projesi (push bildirim özelliği için)

### Adımlar

1. Repoyu klonlayın:
   ```bash
   git clone https://github.com/YasirSayar/ArizaTakip.git
   ```

2. SQL Server'da `ArizaTakip` adında bir veritabanı oluşturun ve aşağıdaki sütunlara sahip bir `ArizaBildirimTakip` tablosu tanımlayın:
   `ID, PC_ADI, IP_ADI, KULLANICI_ADI, SORUN, TARIH, DURUM`

3. `ConnectionHelper.java` içindeki bağlantı bilgilerini kendi SQL Server ortamınıza göre doldurun:
   ```java
   ip = "SUNUCU_IP_ADRESI";
   database = "ArizaTakip";
   uname = "KULLANICI_ADI";
   pass = "SIFRE";
   ```

4. Kendi Firebase projenizi oluşturup `google-services.json` dosyasını `app/` klasörüne ekleyin (bildirim özelliğini kullanmak istiyorsanız).

5. Projeyi Android Studio ile açın, Gradle senkronizasyonunun tamamlanmasını bekleyin ve bir emülatör/cihazda çalıştırın.

## ⚠️ Güvenlik Uyarısı

Bu repoyu herkese açık tutmadan önce gözden geçirmen gereken birkaç kritik nokta var:

- **`HttpHelper.java` içinde gerçek bir FCM Server Key (API anahtarı) açık şekilde kod içine yazılmış.** Bu anahtarın derhal Firebase konsolundan iptal edilip yenisiyle değiştirilmesi, ve yeni anahtarın koddan çıkarılıp `local.properties` veya ortam değişkeni gibi repoya dahil edilmeyen bir yerden okunması önerilir.
- **SQL sorguları, kullanıcı girdisini doğrudan string birleştirme (string concatenation) ile oluşturuyor** (`sql_Veri.java`, `sqlUpdate.java`). Bu, SQL injection riski taşır; parametreli sorgulara (`PreparedStatement`) geçilmesi önerilir.
- **`google-services.json` dosyası da Firebase proje kimlik bilgilerini içerir.** Genellikle bu dosyanın `.gitignore`'a eklenip repoya dahil edilmemesi tercih edilir.
- `ConnectionHelper.java` içindeki sunucu IP/kullanıcı/şifre alanları bu repoda yıldızlarla maskelenmiş durumda; gerçek committed dosyada bu bilgilerin açık olup olmadığını kontrol etmen faydalı olur.

## Lisans

Bu proje [MIT License](./LICENSE) ile lisanslanmıştır.

---

**Geliştirici:** Yasir Sayar
