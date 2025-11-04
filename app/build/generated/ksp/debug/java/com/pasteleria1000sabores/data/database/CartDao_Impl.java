package com.pasteleria1000sabores.data.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pasteleria1000sabores.data.model.CartItem;
import com.pasteleria1000sabores.data.model.CartItemWithProduct;
import com.pasteleria1000sabores.data.model.Product;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
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
public final class CartDao_Impl implements CartDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CartItem> __insertionAdapterOfCartItem;

  private final EntityDeletionOrUpdateAdapter<CartItem> __deletionAdapterOfCartItem;

  private final EntityDeletionOrUpdateAdapter<CartItem> __updateAdapterOfCartItem;

  private final SharedSQLiteStatement __preparedStmtOfClearCart;

  public CartDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCartItem = new EntityInsertionAdapter<CartItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `cart_items` (`id`,`productId`,`quantity`,`customMessage`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CartItem entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getProductId());
        statement.bindLong(3, entity.getQuantity());
        if (entity.getCustomMessage() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCustomMessage());
        }
      }
    };
    this.__deletionAdapterOfCartItem = new EntityDeletionOrUpdateAdapter<CartItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `cart_items` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CartItem entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfCartItem = new EntityDeletionOrUpdateAdapter<CartItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `cart_items` SET `id` = ?,`productId` = ?,`quantity` = ?,`customMessage` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CartItem entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getProductId());
        statement.bindLong(3, entity.getQuantity());
        if (entity.getCustomMessage() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCustomMessage());
        }
        statement.bindLong(5, entity.getId());
      }
    };
    this.__preparedStmtOfClearCart = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM cart_items";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final CartItem cartItem, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCartItem.insert(cartItem);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final CartItem cartItem, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfCartItem.handle(cartItem);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final CartItem cartItem, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfCartItem.handle(cartItem);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearCart(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearCart.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearCart.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<CartItemWithProduct>> getAllCartItems() {
    final String _sql = "SELECT * FROM cart_items";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"products",
        "cart_items"}, true, new Callable<List<CartItemWithProduct>>() {
      @Override
      @Nullable
      public List<CartItemWithProduct> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
            final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
            final int _cursorIndexOfCustomMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "customMessage");
            final LongSparseArray<Product> _collectionProduct = new LongSparseArray<Product>();
            while (_cursor.moveToNext()) {
              final long _tmpKey;
              _tmpKey = _cursor.getLong(_cursorIndexOfProductId);
              _collectionProduct.put(_tmpKey, null);
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipproductsAscomPasteleria1000saboresDataModelProduct(_collectionProduct);
            final List<CartItemWithProduct> _result = new ArrayList<CartItemWithProduct>(_cursor.getCount());
            while (_cursor.moveToNext()) {
              final CartItemWithProduct _item;
              final CartItem _tmpCartItem;
              final int _tmpId;
              _tmpId = _cursor.getInt(_cursorIndexOfId);
              final int _tmpProductId;
              _tmpProductId = _cursor.getInt(_cursorIndexOfProductId);
              final int _tmpQuantity;
              _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
              final String _tmpCustomMessage;
              if (_cursor.isNull(_cursorIndexOfCustomMessage)) {
                _tmpCustomMessage = null;
              } else {
                _tmpCustomMessage = _cursor.getString(_cursorIndexOfCustomMessage);
              }
              _tmpCartItem = new CartItem(_tmpId,_tmpProductId,_tmpQuantity,_tmpCustomMessage);
              final Product _tmpProduct;
              final long _tmpKey_1;
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfProductId);
              _tmpProduct = _collectionProduct.get(_tmpKey_1);
              _item = new CartItemWithProduct(_tmpCartItem,_tmpProduct);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getCartItemByProductId(final int productId,
      final Continuation<? super CartItem> $completion) {
    final String _sql = "SELECT * FROM cart_items WHERE productId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CartItem>() {
      @Override
      @Nullable
      public CartItem call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfCustomMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "customMessage");
          final CartItem _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpProductId;
            _tmpProductId = _cursor.getInt(_cursorIndexOfProductId);
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final String _tmpCustomMessage;
            if (_cursor.isNull(_cursorIndexOfCustomMessage)) {
              _tmpCustomMessage = null;
            } else {
              _tmpCustomMessage = _cursor.getString(_cursorIndexOfCustomMessage);
            }
            _result = new CartItem(_tmpId,_tmpProductId,_tmpQuantity,_tmpCustomMessage);
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
  public LiveData<Integer> getCartItemCount() {
    final String _sql = "SELECT COUNT(*) FROM cart_items";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"cart_items"}, false, new Callable<Integer>() {
      @Override
      @Nullable
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshipproductsAscomPasteleria1000saboresDataModelProduct(
      @NonNull final LongSparseArray<Product> _map) {
    if (_map.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchLongSparseArray(_map, false, (map) -> {
        __fetchRelationshipproductsAscomPasteleria1000saboresDataModelProduct(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`code`,`categoryId`,`name`,`description`,`price`,`imageUrl`,`shape`,`size`,`customizable`,`available` FROM `products` WHERE `id` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      final long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = 0;
      final int _cursorIndexOfCode = 1;
      final int _cursorIndexOfCategoryId = 2;
      final int _cursorIndexOfName = 3;
      final int _cursorIndexOfDescription = 4;
      final int _cursorIndexOfPrice = 5;
      final int _cursorIndexOfImageUrl = 6;
      final int _cursorIndexOfShape = 7;
      final int _cursorIndexOfSize = 8;
      final int _cursorIndexOfCustomizable = 9;
      final int _cursorIndexOfAvailable = 10;
      while (_cursor.moveToNext()) {
        final long _tmpKey;
        _tmpKey = _cursor.getLong(_itemKeyIndex);
        if (_map.containsKey(_tmpKey)) {
          final Product _item_1;
          final int _tmpId;
          _tmpId = _cursor.getInt(_cursorIndexOfId);
          final String _tmpCode;
          _tmpCode = _cursor.getString(_cursorIndexOfCode);
          final int _tmpCategoryId;
          _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
          final String _tmpName;
          _tmpName = _cursor.getString(_cursorIndexOfName);
          final String _tmpDescription;
          if (_cursor.isNull(_cursorIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
          }
          final int _tmpPrice;
          _tmpPrice = _cursor.getInt(_cursorIndexOfPrice);
          final String _tmpImageUrl;
          if (_cursor.isNull(_cursorIndexOfImageUrl)) {
            _tmpImageUrl = null;
          } else {
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
          }
          final String _tmpShape;
          _tmpShape = _cursor.getString(_cursorIndexOfShape);
          final String _tmpSize;
          if (_cursor.isNull(_cursorIndexOfSize)) {
            _tmpSize = null;
          } else {
            _tmpSize = _cursor.getString(_cursorIndexOfSize);
          }
          final boolean _tmpCustomizable;
          final int _tmp;
          _tmp = _cursor.getInt(_cursorIndexOfCustomizable);
          _tmpCustomizable = _tmp != 0;
          final boolean _tmpAvailable;
          final int _tmp_1;
          _tmp_1 = _cursor.getInt(_cursorIndexOfAvailable);
          _tmpAvailable = _tmp_1 != 0;
          _item_1 = new Product(_tmpId,_tmpCode,_tmpCategoryId,_tmpName,_tmpDescription,_tmpPrice,_tmpImageUrl,_tmpShape,_tmpSize,_tmpCustomizable,_tmpAvailable);
          _map.put(_tmpKey, _item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
