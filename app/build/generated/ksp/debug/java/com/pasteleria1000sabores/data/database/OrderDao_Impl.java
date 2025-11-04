package com.pasteleria1000sabores.data.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pasteleria1000sabores.data.model.Order;
import com.pasteleria1000sabores.data.model.OrderItem;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class OrderDao_Impl implements OrderDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Order> __insertionAdapterOfOrder;

  private final EntityInsertionAdapter<OrderItem> __insertionAdapterOfOrderItem;

  private final EntityDeletionOrUpdateAdapter<Order> __deletionAdapterOfOrder;

  private final EntityDeletionOrUpdateAdapter<Order> __updateAdapterOfOrder;

  public OrderDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfOrder = new EntityInsertionAdapter<Order>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `orders` (`id`,`orderNumber`,`totalAmount`,`discount`,`finalAmount`,`status`,`deliveryDate`,`deliveryAddress`,`notes`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Order entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getOrderNumber());
        statement.bindLong(3, entity.getTotalAmount());
        statement.bindLong(4, entity.getDiscount());
        statement.bindLong(5, entity.getFinalAmount());
        statement.bindString(6, entity.getStatus());
        if (entity.getDeliveryDate() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getDeliveryDate());
        }
        if (entity.getDeliveryAddress() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getDeliveryAddress());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getNotes());
        }
        statement.bindLong(10, entity.getCreatedAt());
      }
    };
    this.__insertionAdapterOfOrderItem = new EntityInsertionAdapter<OrderItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `order_items` (`id`,`orderId`,`productId`,`productName`,`quantity`,`unitPrice`,`subtotal`,`customMessage`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final OrderItem entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getOrderId());
        statement.bindLong(3, entity.getProductId());
        statement.bindString(4, entity.getProductName());
        statement.bindLong(5, entity.getQuantity());
        statement.bindLong(6, entity.getUnitPrice());
        statement.bindLong(7, entity.getSubtotal());
        if (entity.getCustomMessage() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getCustomMessage());
        }
      }
    };
    this.__deletionAdapterOfOrder = new EntityDeletionOrUpdateAdapter<Order>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `orders` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Order entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfOrder = new EntityDeletionOrUpdateAdapter<Order>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `orders` SET `id` = ?,`orderNumber` = ?,`totalAmount` = ?,`discount` = ?,`finalAmount` = ?,`status` = ?,`deliveryDate` = ?,`deliveryAddress` = ?,`notes` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Order entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getOrderNumber());
        statement.bindLong(3, entity.getTotalAmount());
        statement.bindLong(4, entity.getDiscount());
        statement.bindLong(5, entity.getFinalAmount());
        statement.bindString(6, entity.getStatus());
        if (entity.getDeliveryDate() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getDeliveryDate());
        }
        if (entity.getDeliveryAddress() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getDeliveryAddress());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getNotes());
        }
        statement.bindLong(10, entity.getCreatedAt());
        statement.bindLong(11, entity.getId());
      }
    };
  }

  @Override
  public Object insertOrder(final Order order, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfOrder.insertAndReturnId(order);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertOrderItems(final List<OrderItem> orderItems,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfOrderItem.insert(orderItems);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOrder(final Order order, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfOrder.handle(order);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateOrder(final Order order, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfOrder.handle(order);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Order>> getAllOrders() {
    final String _sql = "SELECT * FROM orders ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"orders"}, false, new Callable<List<Order>>() {
      @Override
      @Nullable
      public List<Order> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOrderNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "orderNumber");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfDiscount = CursorUtil.getColumnIndexOrThrow(_cursor, "discount");
          final int _cursorIndexOfFinalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "finalAmount");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfDeliveryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryDate");
          final int _cursorIndexOfDeliveryAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryAddress");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<Order> _result = new ArrayList<Order>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Order _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpOrderNumber;
            _tmpOrderNumber = _cursor.getString(_cursorIndexOfOrderNumber);
            final int _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getInt(_cursorIndexOfTotalAmount);
            final int _tmpDiscount;
            _tmpDiscount = _cursor.getInt(_cursorIndexOfDiscount);
            final int _tmpFinalAmount;
            _tmpFinalAmount = _cursor.getInt(_cursorIndexOfFinalAmount);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final String _tmpDeliveryDate;
            if (_cursor.isNull(_cursorIndexOfDeliveryDate)) {
              _tmpDeliveryDate = null;
            } else {
              _tmpDeliveryDate = _cursor.getString(_cursorIndexOfDeliveryDate);
            }
            final String _tmpDeliveryAddress;
            if (_cursor.isNull(_cursorIndexOfDeliveryAddress)) {
              _tmpDeliveryAddress = null;
            } else {
              _tmpDeliveryAddress = _cursor.getString(_cursorIndexOfDeliveryAddress);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new Order(_tmpId,_tmpOrderNumber,_tmpTotalAmount,_tmpDiscount,_tmpFinalAmount,_tmpStatus,_tmpDeliveryDate,_tmpDeliveryAddress,_tmpNotes,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getOrderById(final int orderId, final Continuation<? super Order> $completion) {
    final String _sql = "SELECT * FROM orders WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, orderId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Order>() {
      @Override
      @Nullable
      public Order call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOrderNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "orderNumber");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfDiscount = CursorUtil.getColumnIndexOrThrow(_cursor, "discount");
          final int _cursorIndexOfFinalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "finalAmount");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfDeliveryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryDate");
          final int _cursorIndexOfDeliveryAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryAddress");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final Order _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpOrderNumber;
            _tmpOrderNumber = _cursor.getString(_cursorIndexOfOrderNumber);
            final int _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getInt(_cursorIndexOfTotalAmount);
            final int _tmpDiscount;
            _tmpDiscount = _cursor.getInt(_cursorIndexOfDiscount);
            final int _tmpFinalAmount;
            _tmpFinalAmount = _cursor.getInt(_cursorIndexOfFinalAmount);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final String _tmpDeliveryDate;
            if (_cursor.isNull(_cursorIndexOfDeliveryDate)) {
              _tmpDeliveryDate = null;
            } else {
              _tmpDeliveryDate = _cursor.getString(_cursorIndexOfDeliveryDate);
            }
            final String _tmpDeliveryAddress;
            if (_cursor.isNull(_cursorIndexOfDeliveryAddress)) {
              _tmpDeliveryAddress = null;
            } else {
              _tmpDeliveryAddress = _cursor.getString(_cursorIndexOfDeliveryAddress);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new Order(_tmpId,_tmpOrderNumber,_tmpTotalAmount,_tmpDiscount,_tmpFinalAmount,_tmpStatus,_tmpDeliveryDate,_tmpDeliveryAddress,_tmpNotes,_tmpCreatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getOrderItems(final int orderId,
      final Continuation<? super List<OrderItem>> $completion) {
    final String _sql = "SELECT * FROM order_items WHERE orderId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, orderId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<OrderItem>>() {
      @Override
      @NonNull
      public List<OrderItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "orderId");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfProductName = CursorUtil.getColumnIndexOrThrow(_cursor, "productName");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfUnitPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "unitPrice");
          final int _cursorIndexOfSubtotal = CursorUtil.getColumnIndexOrThrow(_cursor, "subtotal");
          final int _cursorIndexOfCustomMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "customMessage");
          final List<OrderItem> _result = new ArrayList<OrderItem>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final OrderItem _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpOrderId;
            _tmpOrderId = _cursor.getInt(_cursorIndexOfOrderId);
            final int _tmpProductId;
            _tmpProductId = _cursor.getInt(_cursorIndexOfProductId);
            final String _tmpProductName;
            _tmpProductName = _cursor.getString(_cursorIndexOfProductName);
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final int _tmpUnitPrice;
            _tmpUnitPrice = _cursor.getInt(_cursorIndexOfUnitPrice);
            final int _tmpSubtotal;
            _tmpSubtotal = _cursor.getInt(_cursorIndexOfSubtotal);
            final String _tmpCustomMessage;
            if (_cursor.isNull(_cursorIndexOfCustomMessage)) {
              _tmpCustomMessage = null;
            } else {
              _tmpCustomMessage = _cursor.getString(_cursorIndexOfCustomMessage);
            }
            _item = new OrderItem(_tmpId,_tmpOrderId,_tmpProductId,_tmpProductName,_tmpQuantity,_tmpUnitPrice,_tmpSubtotal,_tmpCustomMessage);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
